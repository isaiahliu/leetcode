package p03xx

import util.expect

fun main() {
    class Solution {
        fun coinChange(coins: IntArray, amount: Int): Int {
            val dp = IntArray(amount + 1) { -1 }

            dp[0] = 0

            for (i in 1 until dp.size) {
                var min: Int? = null

                coins.forEach {
                    dp.getOrNull(i - it)?.let { it + 1 }?.takeIf { it > 0 }?.also {
                        min = (min ?: it).coerceAtMost(it)
                    }
                }

                min?.also { dp[i] = it }
            }

            return dp[amount]
        }
    }

    expect {
        Solution().coinChange(
            intArrayOf(
                1, 2, 5
            ), 11
        )
    }
}

