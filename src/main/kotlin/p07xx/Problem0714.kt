package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray, fee: Int): Int {
            val dp = Array(prices.size) { intArrayOf(0, 0) }

            dp[0][0] = -prices[0]

            for (i in 1 until prices.size) {
                val (lastKeep, lastSale) = dp[i - 1]

                dp[i][0] = lastKeep.coerceAtLeast(lastSale - prices[i])
                dp[i][1] = lastSale.coerceAtLeast(lastKeep + prices[i] - fee)
            }

            return dp[dp.lastIndex][1]
        }
    }

    measureTimeMillis {
        Solution().maxProfit(intArrayOf(1, 3, 2, 8, 4, 9), 2).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}