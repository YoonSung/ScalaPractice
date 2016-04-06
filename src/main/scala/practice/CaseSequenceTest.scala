package practice

object CaseSequenceTest {
  def main(args: Array[String]): Unit = {
    assert(10 == withDefault(Some(10)))
    assert(10==withDefault(None))
  }

  val withDefault: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
  }
}
