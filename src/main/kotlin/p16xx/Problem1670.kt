package p16xx

import java.util.*
import util.expect

fun main() {
    class FrontMiddleBackQueue {
        val left = LinkedList<Int>()
        val right = LinkedList<Int>()

        private fun adjust() {
            while (left.size > right.size) {
                right.push(left.pollLast())
            }

            while (right.size - left.size > 1) {
                left.add(right.poll())
            }
        }

        fun pushFront(`val`: Int) {
            left.push(`val`)
            adjust()
        }

        fun pushMiddle(`val`: Int) {
            left.add(`val`)
            adjust()
        }

        fun pushBack(`val`: Int) {
            right.add(`val`)
            adjust()
        }

        fun popFront(): Int {
            return (left.poll() ?: right.poll())?.also { adjust() } ?: -1
        }

        fun popMiddle(): Int {
            return (if (right.size > left.size) right.poll() else left.pollLast())?.also { adjust() } ?: -1
        }

        fun popBack(): Int {
            return right.pollLast()?.also { adjust() } ?: -1
        }
    }


    expect {
        val queue = FrontMiddleBackQueue()
        queue.pushFront(1)
        queue.pushBack(2)
        queue.pushMiddle(3)
        queue.pushMiddle(4)
        queue.popFront()
        queue.popMiddle()
        queue.popMiddle()
        queue.popBack()
        queue.popFront()
    }
}

