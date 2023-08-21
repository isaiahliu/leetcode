package p06xx

import util.expect

fun main() {
    class MyCircularDeque(val k: Int) {
        val queue = IntArray(k)
        var head = 0
        var tail = 0
        var size = 0

        fun insertFront(value: Int): Boolean {
            if (isFull()) {
                return false
            }

            if (size++ > 0) {
                head = (head - 1 + k) % k
            }

            queue[head] = value

            return true
        }

        fun insertLast(value: Int): Boolean {
            if (isFull()) {
                return false
            }

            if (size++ > 0) {
                tail = (tail + 1) % k
            }

            queue[tail] = value

            return true
        }

        fun deleteFront(): Boolean {
            if (isEmpty()) {
                return false
            }

            if (size-- > 1) {
                head = (head + 1) % k
            }

            return true
        }

        fun deleteLast(): Boolean {
            if (isEmpty()) {
                return false
            }

            if (size-- > 1) {
                tail = (tail - 1 + k) % k
            }

            return true
        }

        fun getFront(): Int {
            return if (isEmpty()) {
                -1
            } else {
                queue[head]
            }
        }

        fun getRear(): Int {
            return if (isEmpty()) {
                -1
            } else {
                queue[tail]
            }
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun isFull(): Boolean {
            return size == k
        }
    }

    expect {
        val q = MyCircularDeque(3)

        q.insertLast(1)
        q.insertLast(2)
        q.insertFront(3)
        q.insertFront(4)
        q.getRear()
        q.isFull()
        q.deleteLast()
        q.insertFront(4)
        q.getFront()
    }
}