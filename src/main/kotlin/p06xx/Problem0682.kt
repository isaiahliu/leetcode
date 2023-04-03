package p06xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun calPoints(operations: Array<String>): Int {
            val scores = LinkedList<Int>()
            var sum = 0

            operations.forEach {
                when (it) {
                    "+" -> {
                        scores.push(scores[0] + scores[1])
                        sum += scores.peek()
                    }

                    "C" -> {
                        sum -= scores.pop()
                    }

                    "D" -> {
                        scores.push(scores.peek() * 2)
                        sum += scores.peek()
                    }

                    else -> {
                        scores.push(it.toInt())
                        sum += scores.peek()
                    }
                }
            }

            return sum
        }
    }

    measureTimeMillis {
        Solution().calPoints(arrayOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}