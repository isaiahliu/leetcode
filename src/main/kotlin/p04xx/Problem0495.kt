package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
            if (duration == 0) {
                return 0
            }

            var result = duration * timeSeries.size

            for (i in 1 until timeSeries.size) {
                val diff = timeSeries[i] - timeSeries[i - 1]

                if (diff < duration) {
                    result -= duration - diff
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findPoisonedDuration(
            intArrayOf(1, 4), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}