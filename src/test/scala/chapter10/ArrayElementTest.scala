package chapter10

import org.scalatest.{WordSpec}

import scala.chapter10.ArrayElement

class ArrayElementTest extends WordSpec {
  "ArrayElement(1, 2, 3) and ArrayElement(*, **, ***)" when {
    var element1 = new ArrayElement(Array("1", "2", "3"))
    var element2 = new ArrayElement(Array("*", "**", "***"))

    "beside" should {
      "equal to 1*\n2**\n3***" in {
        assert((element1 beside element2).toString === "1*\n2**\n3***")
      }
    }
  }
}
