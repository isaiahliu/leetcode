package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMaximumConsecutive(coins: IntArray): Int {
            var result = 1

            for (num in coins.sorted()) {
                if (num > result) {
                    break
                } else {
                    result += num
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getMaximumConsecutive(
            intArrayOf(1, 4, 10, 3, 1)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
