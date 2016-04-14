package baseball

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

  def userInput(): List[Int] = List(1, 2, 3)
}