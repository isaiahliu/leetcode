package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun stoneGameVIII(stones: IntArray): Int {
            for (i in 1 until stones.size) {
                stones[i] += stones[i - 1]
            }

            var result = stones[stones.lastIndex]

            for (index in stones.lastIndex - 1 downTo 1) {
                result = result.coerceAtLeast(stones[index] - result)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().stoneGameVIII(
            intArrayOf(-1, 2, -3, 4, -5)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
