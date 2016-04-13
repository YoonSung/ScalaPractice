package chapter19

class YQueue[T] private (
  private val leading: List[T],
  private val trailing: List[T]
) {

  private def this(elems: T*) = this(elems.toList, Nil)

  private def mirror =
      if (leading.isEmpty)
        new YQueue[T](trailing.reverse, leading)
      else
        this

  def head = mirror.leading.head
  def tail = {
    val q = mirror
    new YQueue[T](q.leading.tail, q.trailing)
  }

  def enqueue(x: T) = new YQueue[T](leading, x::trailing)
  def size: Int = leading.size + trailing.size
}

object YQueue {
  def apply[T](xs: T*) = new YQueue[T](xs.toList, Nil)
}