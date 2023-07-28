package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSumOfLengths(arr: IntArray, target: Int): Int {
            val sumMap = hashMapOf(0 to -1)
            var sum = 0

            val dp = Array(arr.size + 1) { IntArray(2) { arr.size + 1 } }
            arr.forEachIndexed { index, num ->
                sum += num

                sumMap[sum - target]?.also {
                    val size = index - it

                    dp[it + 1][1] = dp[it + 1][1].coerceAtMost(size)
                    dp[index + 1][0] = dp[index + 1][1].coerceAtMost(size)
                }

                sumMap[sum] = index
            }

            for (i in 1 until dp.size) {
                dp[i][0] = dp[i][0].coerceAtMost(dp[i - 1][0])
            }
            var result = arr.size + 1

            for (i in dp.lastIndex - 1 downTo 0) {
                dp[i][1] = dp[i][1].coerceAtMost(dp[i + 1][1])

                result = result.coerceAtMost(dp[i][0] + dp[i][1])
            }

            return result.takeIf { it <= arr.size } ?: -1
        }
    }

    measureTimeMillis {
        Solution().minSumOfLengths(
            intArrayOf(3, 3), 3
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

