package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minCostClimbingStairs(cost: IntArray): Int {
            val dp = IntArray(cost.size + 1)

            for (i in 2 until dp.size) {
                dp[i] = (dp[i - 2] + cost[i - 2]).coerceAtMost(dp[i - 1] + cost[i - 1])
            }

            return dp[cost.size]
        }
    }

    measureTimeMillis {
        Solution().minCostClimbingStairs(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}