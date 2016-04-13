package chapter19

class YQueue[T] (
  private val leading: List[T],
  private val trailing: List[T]
) {
  private def mirror =
      if (leading.isEmpty)
        new YQueue(trailing.reverse, leading)
      else
        this

  def head = mirror.leading.head
  def tail = {
    val q = mirror
    new YQueue(q.leading.tail, q.trailing)
  }

  def enqueue(x: T) = new YQueue(leading, x::trailing)
  def size: Int = leading.size + trailing.size
}