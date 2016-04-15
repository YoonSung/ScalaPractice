package enumeration

import org.scalatest.WordSpec

class EnumTest extends WordSpec {

  "Enumerate" when {
    "check" should {
      assert(Direction.North.id === 0)
    }
  }

  object Direction extends Enumeration {
    var North = Value("north")
    var East = Value("east")
    var South = Value("south")
    var West = Value("west")
  }
}
