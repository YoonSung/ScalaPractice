package practice

abstract class Bird
trait Swimmable {
  def swim: Unit = {
    println("I'm swimming!")
  }
}
trait Flyable {
  protected def flyMessage: String
  def fly: Unit = {
    println(flyMessage)
  }
}

class Penguin extends Bird with Swimmable
class Pigeon extends Bird with Swimmable with Flyable {
  override def flyMessage: String = "I am Pigeon! I can Fly!"
}
class Hawk extends Bird with Swimmable with Flyable {
  override def flyMessage: String = "I am Hawk! I can Fly!"
}
class FrigateBird extends Bird with Flyable {
  override def flyMessage: String = "I am FrigateBird! I can Fly!"
}

/*
비둘기(pigeon), 매(hawk), 펭귄(penguin), 군함조(frigatebird) 총 4가지 종류의 새가 있다.
비둘기, 매, 펭귄은 수영이 가능하고, 비둘기, 매, 군함조는 날 수 있어야 한다.
비둘기, 매, 펭귄은 swim method를 호출하면 "I'm swimming!"을 출력해야 한다.
비둘기는 fly method를 호출하면 "I'm a good flyer!"를 출력하고, 매, 군함조는 "I'm an excellent flyer!"를 출력해야 한다.
위 요구사항을 상속과 trait를 활용해 구현한다.
 */
object TraitPractice {
  def main(args:Array[String]):Unit = {
    val penguin = new Penguin
    penguin.swim

    val pigeon = new Pigeon
    pigeon.swim
    pigeon.fly

    val hawk = new Hawk
    hawk.swim
    hawk.fly

    val frigateBird = new FrigateBird
    frigateBird.fly
  }
}