package p08xx

import util.expect

fun main() {
    class Solution {
        fun new21Game(n: Int, k: Int, maxPts: Int): Double {
            val r = 1.0 / maxPts
            val dp = DoubleArray(n + maxPts + 1)

            dp[0] = 1.0
            dp[1] = -1.0

            var result = 0.0
            for (i in 0..n) {
                dp[i] += dp.getOrNull(i - 1) ?: 0.0

                if (i < k) {
                    dp[i + 1] += dp[i] * r
                    dp[i + maxPts + 1] -= dp[i] * r
                } else {
                    result += dp[i]
                }
            }

            return result
        }
    }

    expect {
        Solution().new21Game(
            0, 0, 1
        )
    }
}