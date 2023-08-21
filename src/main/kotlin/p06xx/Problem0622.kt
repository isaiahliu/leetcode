package p06xx

import util.expect

fun main() {
    class MyCircularQueue(val k: Int) {
        val queue = IntArray(k)
        var headIndex = 0
        var tailIndex = 0
        var size = 0

        fun enQueue(value: Int): Boolean {
            if (isFull()) {
                return false
            }

            if (size > 0) {
                tailIndex = (tailIndex + 1) % k
            }

            queue[tailIndex] = value
            size++
            return true
        }

        fun deQueue(): Boolean {
            if (isEmpty()) {
                return false
            }

            size--

            if (size > 0) {
                headIndex = (headIndex + 1) % k
            }
            return true
        }

        fun Front(): Int {
            return if (!isEmpty()) queue[headIndex] else -1
        }

        fun Rear(): Int {
            return if (!isEmpty()) queue[tailIndex] else -1
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun isFull(): Boolean {
            return size == k
        }
    }

    expect {
        MyCircularQueue(1)

    }
}