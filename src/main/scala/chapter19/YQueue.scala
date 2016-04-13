package chapter19

trait YQueue[T] {
  def head: T
  def tail: YQueue[T]
  def enqueue(x: T): YQueue[T]
  def size: Int
}

object YQueue {
  def apply[T](xs: T*): YQueue[T] = new YQueueImpl[T](xs.toList, Nil)

  private class YQueueImpl[T] (private val leading:List[T], private val trailing:List[T]) extends YQueue[T] {
    private def this(elems: T*) = this(elems.toList, Nil)

    private def mirror =
      if (leading.isEmpty)
        new YQueueImpl[T](trailing.reverse, leading)
      else
        this

    override def head = mirror.leading.head
    override def tail = {
      val q = mirror
      new YQueueImpl[T](q.leading.tail, q.trailing)
    }

    override def enqueue(x: T) = new YQueueImpl[T](leading, x::trailing)
    override def size: Int = leading.size + trailing.size
  }
}