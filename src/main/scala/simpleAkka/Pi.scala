package simpleAkka

import akka.actor.{ActorSystem, Props, ActorRef, Actor}
import akka.routing.RoundRobinPool
import scala.concurrent.duration._

/**
 * @see http://doc.akka.io/docs/akka/2.0/intro/getting-started-first-scala.html
 *
 * Calculate – sent to the Master actor to start the calculation
 * Work – sent from the Master actor to the Worker actors containing the work assignment
 * Result – sent from the Worker actors to the Master actor containing the result from the worker’s calculation
 * PiApproximation – sent from the Master actor to the Listener actor containing the the final pi result and how long time the calculation took
 */
object Pi extends App {
  calculate(nrOfWorkers = 4, nrOfElements = 10000, nrOfMessages = 10000)

  // actors and messages ...
  sealed trait PiMessage
  case object Calculate extends PiMessage
  case class Work(start: Int, nrOfElements: Int) extends PiMessage
  case class Result(value: Double) extends PiMessage
  case class PiApproximation(pi: Double, duration: Duration)

  class Worker extends Actor {

    def calculatePiFor(start: Int, nrOfElements: Int): Double = {
      var acc = 0.0
      for (i ← start until (start + nrOfElements))
        acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
      acc
    }

    override def receive: Receive = {
      case Work(start, nrOfElements) =>
        sender ! Result(calculatePiFor(start, nrOfElements))
    }
  }

  /**
   * nrOfWorkers – defining how many workers we should start up
   * nrOfMessages – defining how many number chunks to send out to the workers
   * nrOfElements – defining how big the number chunks sent to each worker should be
   * ActorRef - This is used to report the the final result to the outside world.
   */
  class Master(nrOfWorkers: Int, nrOfMessages: Int, nrOfElements: Int, listener: ActorRef) extends Actor {
    var pi: Double = _
    var nrOfResults: Int = _
    val start: Long = System.currentTimeMillis

    val workerRouter = context.actorOf(
      Props[Worker].withRouter(RoundRobinPool(nrOfWorkers)),
      name = "workerRouter"
    )

    override def receive: Receive = {
      case Calculate =>
        for (i <- 0 until nrOfMessages) workerRouter ! Work(i * nrOfElements, nrOfElements)

      case Result(value) =>
        pi += value
        nrOfResults += 1
        if (nrOfResults == nrOfMessages) {
          // Send the result to the listener
          listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)

          // Stop this actor and all its supervised children
          context.stop(self)
        }
    }
  }

  class Listener extends Actor {
    override def receive: Receive = {
      case PiApproximation(pi, duration) =>
        println("\n\tPi approximation: \t\t%s\n\tcalculation time : \t%s".format(pi, duration))
        context.system.shutdown()
    }
  }

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int): Unit = {
    // Create an Akka system
    /**
     * Akka container which will contain all actors created in that “context”.
     * An example of how to create actors in the container is the ‘system.actorOf(...)’ line in the calculate method.
     * In this case we create two top level actors.
     * If you instead where in an actor context,
     * i.e. inside an actor creating other actors,
     * you should use context.actorOf(...). This is illustrated in the Master code above.
     */
    val system = ActorSystem("PiSystem")

    // create the result listener, which will print the result and shutdown the system
    val listener = system.actorOf(Props[Listener], name = "listener")

    // create the master
    val master = system.actorOf(Props(new Master(
      nrOfWorkers, nrOfMessages, nrOfElements, listener)),
      name = "master")

    // start the calculation
    master ! Calculate

  }
}