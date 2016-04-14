package baseball

import org.scalatest.{WordSpec}

class BaseballGameTest extends WordSpec {
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
  "baseball game" when {
    "generateUserInput" should {
      val userInputs = baseball.userInput
      "returned list size must be 3" in {
        assert(userInputs.size === 3)
      }
    }

    "generateAnswer" should {
      val answers = baseball.answers
      "returnd list size must be 3" in {
        assert(answers.size === 3)
      }
    }
  }
}
