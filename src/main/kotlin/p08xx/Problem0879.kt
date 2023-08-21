package p08xx

import util.expect

fun main() {
    class Solution {
        fun profitableSchemes(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int {
            val m = 1000000007

            val dp = Array(n + 1) { IntArray(minProfit + 1).also { it[0] = 1 } }

            for (groupIndex in 1..group.size) {
                for (staffCount in n downTo group[groupIndex - 1]) {
                    for (p in minProfit downTo 0) {
                        dp[staffCount][p] += dp[staffCount - group[groupIndex - 1]][(p - profit[groupIndex - 1]).coerceAtLeast(
                            0
                        )]
                        dp[staffCount][p] %= m
                    }
                }
            }
            return dp[n][minProfit]
        }
    }

    expect {
        Solution().profitableSchemes(
            100,
            100,
            intArrayOf(1),
            intArrayOf(1),
        )

    }
}