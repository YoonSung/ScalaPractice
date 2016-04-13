package chapter19

import org.scalatest.WordSpec

class QueueTest extends WordSpec {
  "Queue not modified" when {
    val queue = YQueue[Int](1, 2, 3)
    val queue2 = queue enqueue 4

    "queue" should {
      "not added to variable queue" in {
        assert(queue.size === 3)
        assert(queue != queue2)
      }
    }
  }
}