package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray): Int {
            val dp = Array(prices.size) { intArrayOf(0, 0) }

            var maxDiff = 0
            var min = prices[0]

            for (i in dp.indices) {
                if (prices[i] < min) {
                    min = prices[i]
                } else {
                    maxDiff = maxDiff.coerceAtLeast(prices[i] - min)
                }
                dp[i][0] += maxDiff
            }

            var result = 0

            maxDiff = 0
            var max = prices[prices.lastIndex]
            for (i in dp.lastIndex downTo 0) {
                if (prices[i] > max) {
                    max = prices[i]
                } else {
                    maxDiff = maxDiff.coerceAtLeast(max - prices[i])
                }
                dp[i][1] += maxDiff

                result = result.coerceAtLeast(dp[i][0] + dp[i][1])
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxProfit(intArrayOf(0, 8, 5, 7, 4, 7)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

