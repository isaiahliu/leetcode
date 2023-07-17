package plcp

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun processTasks(tasks: Array<IntArray>): Int {
            var result = 0
            val taskQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            (tasks.sortedBy { it[0] } + intArrayOf(1000000001, 1000000001, 1)).forEach { (start, end, period) ->
                while (!taskQueue.isEmpty() && taskQueue.peek().first + result < start) {
                    if (taskQueue.peek().first + result >= taskQueue.peek().second) {
                        taskQueue.poll()
                    } else {
                        result += taskQueue.peek().second.coerceAtMost(start) - (taskQueue.peek().first + result)
                    }
                }
                taskQueue.offer(end - period + 1 - result to end + 1)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().processTasks(
            arrayOf(
                intArrayOf(1, 10, 1),
                intArrayOf(2, 9, 1),
                intArrayOf(3, 8, 1),
                intArrayOf(4, 7, 1),
                intArrayOf(5, 6, 1),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
