package baseball

import scala.io.StdIn

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

  def answers(): List[Int] = List(1, 2, 3)

  def parseToList(string: String): List[Int] = string.split(",").map(_.trim).map(_.toInt).toList

  def userInput(): List[Int] = {
    print("Please Input Expected Score Seperated by ','")
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