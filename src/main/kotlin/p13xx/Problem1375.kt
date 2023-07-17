package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTimesAllBlue(flips: IntArray): Int {
            var max = 0
            var result = 0

            flips.forEachIndexed { index, num ->
                max = max.coerceAtLeast(num)
                result += (index + 1) / max
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numTimesAllBlue(
            intArrayOf(3, 2, 4, 1, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

