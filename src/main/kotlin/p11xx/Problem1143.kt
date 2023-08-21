package p11xx

import util.expect

fun main() {
    class Solution {
        fun longestCommonSubsequence(text1: String, text2: String): Int {
            val dp = Array(text1.length + 1) {
                IntArray(text2.length + 1)
            }

            for (index1 in 1..text1.length) {
                for (index2 in 1..text2.length) {
                    dp[index1][index2] = dp[index1 - 1][index2].coerceAtLeast(dp[index1][index2 - 1])

                    if (text1[index1 - 1] == text2[index2 - 1]) {
                        dp[index1][index2] = dp[index1][index2].coerceAtLeast(dp[index1 - 1][index2 - 1] + 1)
                    }
                }
            }

            return dp.last().last()
        }
    }

    expect {
        Solution().longestCommonSubsequence(
            "abcde",
            "ace"
        )
    }
}

