package p00xx

import util.expect

fun main() {
    class Solution {
        fun numDecodings(s: String): Int {
            val dp = IntArray(s.length)

            if (s[s.lastIndex] > '0') {
                dp[s.lastIndex] = 1
            }

            for (i in dp.size - 2 downTo 0) {
                val n1 = s[i] - '0'
                val n2 = s[i + 1] - '0'

                if (n1 > 0) {
                    dp[i] += dp[i + 1]

                    if (n1 * 10 + n2 <= 26) {
                        dp[i] += dp.getOrNull(i + 2) ?: 1
                    }
                }
            }

            return dp[0]
        }
    }

    expect {
        Solution().numDecodings(
            "12"
        )
    }
}

