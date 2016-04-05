package scala.chapter10

class ArrayElement(
  val contents: Array[String]
) extends Element {
  def above(that: Element): Element = {
    new ArrayElement(this.contents ++ that.contents)
  }

  def beside(that: Element): Element = {
    new ArrayElement(for ((line1, line2) <- this.contents zip that.contents) yield {
      line1 + line2
    })
  }


  //  def beside(that: scala.chapter10.Element): scala.chapter10.Element = {
  //    def contents = new Array[String](this.contents.length)
  //    for (i <- 0 until this.contents.length) {
  //      contents(i) = this.contents(i) + that.contents(i)
  //    }
  //
  //    new scala.chapter10.ArrayElement(contents);
  //  }
  override def toString: String = contents mkString "\n"
}