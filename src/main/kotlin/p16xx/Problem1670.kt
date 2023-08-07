package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

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


    measureTimeMillis {
        val queue = FrontMiddleBackQueue()
        queue.pushFront(1).also { println("${it} should be $it") }
        queue.pushBack(2).also { println("${it} should be $it") }
        queue.pushMiddle(3).also { println("${it} should be $it") }
        queue.pushMiddle(4).also { println("${it} should be $it") }
        queue.popFront().also { println("${it} should be 1") }
        queue.popMiddle().also { println("${it} should be 3") }
        queue.popMiddle().also { println("${it} should be 4") }
        queue.popBack().also { println("${it} should be 2") }
        queue.popFront().also { println("${it} should be -1") }
    }.also { println("Time cost: ${it}ms") }
}

