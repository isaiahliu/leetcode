package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeCoveredIntervals(intervals: Array<IntArray>): Int {
            intervals.sortWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })

            var result = intervals.size

            var last = -1

            intervals.forEach { (_, end) ->
                if (end > last) {
                    last = end
                } else {
                    result--
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().removeCoveredIntervals(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 4),
                intArrayOf(3, 4),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
