package scala.chapter10

abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  //  def width: Int = if (height == 0) 0 else contents(0).length
  def width: Int = if (height == 0) {
    0
  } else {
    contents(0).length
  }
}

object Element {
  def elem(contents: Array[String]): Element = {
    new ArrayElement(contents)
  }
  //...
}