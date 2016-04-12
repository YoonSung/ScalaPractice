package webserver

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{StatusCodes, StatusCode}
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import spray.json.DefaultJsonProtocol._
import scala.concurrent.duration._
import scala.io.StdIn
import scala.util.Try

object WebServer3 {
  case class Bid(userId: String, bid: Int)
  case object GetBids
  case class Bids(bids: List[Bid])

  case class AuctionResult(code: StatusCode, message: String)
  class Auction extends Actor {
    override def receive: Receive = {
      case Bid => {
        println("Bid")
        sender ! AuctionResult(StatusCodes.Created, "Bid")
      }
      case GetBids => {
        println("GetBids")
        sender ! AuctionResult(StatusCodes.NoContent, "GetBids")
      }
      case _ => {
        println("_")
        sender ! AuctionResult(StatusCodes.BadRequest, "_")
      }
    }
  }

  // these are from spray-json
  implicit val bidFormat = jsonFormat2(Bid)
  implicit val bidsFormat = jsonFormat1(Bids)

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplite in the end
    implicit val executionContext = system.dispatcher
    val auction: ActorRef = system.actorOf(Props(new Auction), "auction")

    val route = path("auction") {

      println("path auction!")

      post {
        parameter("bid".as[Int], "user") { (bid, user) =>

          println(s"bid : $bid")
          println(s"user : $user")

          // place a bid, fire-and-forget
          auction ! Bid(user, bid)
          // 왜 multi parameter가 가능한거지?
          complete(StatusCodes.Accepted, "bid placed")
        }
      } ~
      get {
        implicit val timeout: Timeout = 5.seconds

        // query the actor for the current auction state
        val result = (auction ? GetBids);
        result.map(result => {
          println(s"$result.code, $result.message")
        })

        complete(StatusCodes.Accepted, "ttt")
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()  // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind())  // trigger unbinding from the port
      .onComplete(_ => system.terminate())  // and shutdown when done
  }
}