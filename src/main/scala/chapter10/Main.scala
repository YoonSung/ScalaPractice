package scala.chapter10

object Main extends App {
  var element1 = new ArrayElement(Array("1", "2", "3"))
  var element2 = new ArrayElement(Array("*", "**", "***"))
  var resultElement: Element = element1 beside element2

  println(resultElement.toString)
}
