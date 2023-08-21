package p07xx

import util.expect

fun main() {
    class Solution {
        fun minCostClimbingStairs(cost: IntArray): Int {
            for (i in 2 until cost.size) {
                cost[i] += cost[i - 2].coerceAtMost(cost[i - 1])
            }

            return cost[cost.lastIndex].coerceAtMost(cost[cost.lastIndex - 1])
        }
    }

    expect {
        Solution().minCostClimbingStairs(
            intArrayOf()
        )
    }
}