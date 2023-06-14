package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTimesAllBlue(flips: IntArray): Int {
            return flips.foldIndexed(intArrayOf(0, 0)) { index, result, num ->
                result[0] = result[0].coerceAtLeast(num)
                result[1] += (index + 1) / result[0]

                result
            }[1]
        }
    }

    measureTimeMillis {
        Solution().numTimesAllBlue(
            intArrayOf(3, 2, 4, 1, 5)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

