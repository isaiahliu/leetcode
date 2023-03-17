package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray): Int {
            if (prices.size <= 2) {
                return (prices[prices.lastIndex] - prices[0]).coerceAtLeast(0)
            }

            val dp = Array(prices.size) {
                //0 -- sale/stay
                //1 -- purchase/keep
                intArrayOf(0, 0)
            }

            dp[0][1] = -prices[0]
            dp[1][1] = (-prices[1]).coerceAtLeast(-prices[0])
            dp[1][0] = (prices[1] - prices[0]).coerceAtLeast(0)

            for (i in 2 until dp.size) {
                val dm1Stay = dp[i - 1][0]
                val dm1Keep = dp[i - 1][1]
                val dm2Stay = dp[i - 2][0]
                val dm2Keep = dp[i - 2][1]

                dp[i][0] = dm1Stay.coerceAtLeast(dm1Keep + prices[i])
                dp[i][1] = dm1Keep.coerceAtLeast(dm2Stay - prices[i])
            }

            return dp[dp.lastIndex][0]
        }
    }

    measureTimeMillis {
        Solution().maxProfit(intArrayOf(1, 2, 3, 0, 2)).also { println(it) }
    }
}

