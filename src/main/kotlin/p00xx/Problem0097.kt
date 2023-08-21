package p00xx

import util.expect

fun main() {
    class Solution {
        fun isInterleave(s1: String, s2: String, s3: String): Boolean {
            if (s3.length != s1.length + s2.length) {
                return false
            }

            val dp = Array(s1.length + 1) {
                BooleanArray(s2.length + 1)
            }

            dp[0][0] = true
            for (r in 1..s1.length) {
                dp[r][0] = s1[r - 1] == s3[r - 1]
                if (!dp[r][0]) {
                    break
                }
            }

            for (c in 1..s2.length) {
                dp[0][c] = s2[c - 1] == s3[c - 1]
                if (!dp[0][c]) {
                    break
                }
            }

            for (r in 1..s1.length) {
                for (c in 1..s2.length) {
                    dp[r][c] = dp[r - 1][c] && s1[r - 1] == s3[r + c - 1] || dp[r][c - 1] && s2[c - 1] == s3[r + c - 1]
                }
            }

            return dp[s1.length][s2.length]
        }
    }

    expect {
        Solution().isInterleave(
            "aabcc", "dbbca", "aadbbcbcac"
        )
    }
}

