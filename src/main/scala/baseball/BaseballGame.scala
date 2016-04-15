package baseball

import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util.Random

class BaseballGame {
  def evaluate(userInputs: List[Int], answers: List[Int]): (Int, Int) = {
    var strike: Int = 0;
    var ball: Int = 0;
    answers.foreach(answer => {
      if (userInputs.contains(answer)) {
        if (userInputs.indexOf(answer) == answers.indexOf(answer))
          strike+=1
        else
          ball+=1
      }
    })

    (strike, ball)
  }

  def answers(): List[Int] = {
    val random = new Random(System.currentTimeMillis)
    var answers = ListBuffer[Int]();
    while(answers.size != 3) {
      val rand = random.nextInt(10)
      answers.find(answer => answer == rand) match {
        case Some(_) => {}
        case _ => answers += rand
      }
    }
    return answers.toList
  }

  def parseToList(string: String): List[Int] = string.split(",").map(_.trim).map(_.toInt).toList

  def userInput(): List[Int] = {
    print("Please Input 3 Numbers ex) > 1, 2, 3")
    StdIn.readLine() match {
      case null => Nil
      case inputString: String => {
        try {
          val parsedList = parseToList(inputString)
          if (parsedList.size != 3)
            throw new IllegalArgumentException
          else
            parsedList
        } catch {
          case _: Throwable => throw new IllegalArgumentException
        }
      }
    }
  }
}

object BaseballGame {
  def apply() = new BaseballGame
}