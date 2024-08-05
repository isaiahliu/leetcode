package p31xx

import util.expect

fun main() {
    class Solution {
        fun numberOfStableArrays(zero: Int, one: Int, limit: Int): Int {
            val m = 1000000007L
            val dp = Array(zero + 1) {
                Array(one + 1) {
                    LongArray(2)
                }
            }
            for (i in 0..minOf(zero, limit)) {
                dp[i][0][0] = 1
            }
            for (j in 0..minOf(one, limit)) {
                dp[0][j][1] = 1
            }

            for (i in 1..zero) {
                for (j in 1..one) {
                    if (i > limit) {
                        dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] - dp[i - limit - 1][j][1]
                    } else {
                        dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1]
                    }
                    dp[i][j][0] = (dp[i][j][0] % m + m) % m
                    if (j > limit) {
                        dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0] - dp[i][j - limit - 1][0]
                    } else {
                        dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0]
                    }
                    dp[i][j][1] = (dp[i][j][1] % m + m) % m
                }
            }
            return ((dp[zero][one][0] + dp[zero][one][1]) % m).toInt()
        }
    }

    expect {
        Solution().numberOfStableArrays(
            77, 80, 94
        )
    }
}
