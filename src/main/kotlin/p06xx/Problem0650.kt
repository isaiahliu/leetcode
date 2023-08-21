package p06xx

import util.expect

fun main() {
    class Solution {
        fun minSteps(n: Int): Int {
            if (n == 1) {
                return 0
            }

            val dp = IntArray(n + 1) { it }
            dp[1] = 2

            for (i in 2..n / 2) {
                if (dp[i] < Int.MAX_VALUE) {
                    var count = dp[i] + 2
                    for (j in i * 2..n step i) {
                        dp[j] = dp[j].coerceAtMost(count++)
                    }
                }
            }

            return dp[n]
        }
    }

    expect {
        Solution().minSteps(
            3
        )
    }
}