package baseball

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, WordSpec}

class BaseballGameTest extends WordSpec with MockFactory with BeforeAndAfter {
  /**
   * baseball game
   *
   * <Use Scenario>
   * 1. Input = input 창에 ,단위로 3가지 숫자를 입력한다. 숫자는 1 <= x <= 30 의 숫자를 입력한다.
   * 2. Answer = 랜덤한 숫자값 3개를 만든다.
   * 3. Input과 Answer값을 비교해서 맞은갯수를 반환한다.
   * 4. Three Out이 될때까지 1-2번을 반복한다.
   *
   * <Test Case>
   * 1. input = 3개의 숫자가 담긴 List를 반환
   * 2. answer = 3개의 숫자가 담긴 List를 반환
   * 3. mock을 사용하도록 변경.
   *
   *
   */

  val baseball = new BaseballGame
  //TODO It is working weird
  //var baseball:BaseballGame = _
  //before {
  //  baseball = new BaseballGame
  //}
  //TODO prevent assert method to kill the program.

  "baseball game" when {
    "generateUserInput" should {
      val dut = stub[BaseballGame]
      (dut.userInput _).when().returns(List(1,2,3))
      val userInputs = dut.userInput
      "returned list size must be 3" in {
        assert(userInputs.size === 3)
      }
    }

    "generateAnswer" should {
      val answerList = baseball.answers()
      "list size must be 3" in {
        assert(answerList.size === 3)
      }

      "list has no duplicated element" should {
        answerList.foreach(answer=> assert(answerList.count(_ == answer) === 1))
      }
    }

    "get score" should {
      "strike 0 ball 3" in {
        val (strike, ball) = baseball.evaluate(List(1,2,3), List(3,1,2))
        assert(strike === 0)
        assert(ball === 3)
      }

      "strike 2 ball 0" in {
        val (strike, ball) = baseball.evaluate(List(1,2,3), List(1,2,0))
        assert(strike === 2)
        assert(ball === 0)
      }

      "strike 1 ball 2" in {
        val (strike, ball) = baseball.evaluate(List(1,2,3), List(1,3,2))
        assert(strike === 1)
        assert(ball === 2)
      }
    }
  }
}
