package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMinDifference(timePoints: List<String>): Int {
            val minutes = timePoints.map {
                it.split(":").map { it.toInt() }.let { (h, m) -> h * 60 + m }
            }.sorted()

            var result = 24 * 60 + minutes[0] - minutes[minutes.lastIndex]

            for (i in 1 until minutes.size) {
                result = result.coerceAtMost(minutes[i] - minutes[i - 1])
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findMinDifference(listOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}