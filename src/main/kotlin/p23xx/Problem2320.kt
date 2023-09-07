package p23xx

import util.expect

fun main() {
    class Solution {
        fun countHousePlacements(n: Int): Int {
            val m = 1000000007

            val dp = longArrayOf(1, 1)

            repeat(n - 1) {
                val (lastEmpty, lastUsed) = dp

                dp[0] = (lastEmpty + lastUsed) % m
                dp[1] = lastEmpty
            }

            return (((dp[0] + dp[1]) % m).let { it * it } % m).toInt()
        }
    }

    expect {
        Solution().countHousePlacements(
            5
        )
    }
}