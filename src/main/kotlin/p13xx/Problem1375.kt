package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTimesAllBlue(flips: IntArray): Int {
            return flips.foldIndexed(0 to 0) { index, (max, count), num ->
                max.coerceAtLeast(num).let {
                    it to count + (index + 1) / it
                }
            }.second
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

