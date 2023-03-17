package p05xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
            val queue = LinkedList(capital.mapIndexed { index, c -> c to profits[index] }.sortedBy { it.first })

            val profitQueue = PriorityQueue<Int>(compareByDescending { it })
            var result = w

            for (i in 0 until k) {
                while (queue.isNotEmpty() && queue.peek().first <= result) {
                    profitQueue.offer(queue.pop().second)
                }

                result += profitQueue.poll() ?: break
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findMaximizedCapital(
            1, 2, intArrayOf(1, 2, 3), intArrayOf(1, 1, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}