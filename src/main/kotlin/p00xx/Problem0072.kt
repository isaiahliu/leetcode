package p00xx

import util.expect

fun main() {
    class Solution {
        fun minDistance(word1: String, word2: String): Int {
            //dp[m][n] = word1第m个字母标称word2第n个字母的最小操作
            val dp = Array(word1.length + 1) { r ->
                IntArray(word2.length + 1) { c ->
                    r + c
                }
            }

            for (m in 1..word1.length) {
                for (n in 1..word2.length) {
                    val char1 = word1[m - 1]
                    val char2 = word2[n - 1]

                    val p1 = dp[m][n - 1] + 1
                    val p2 = dp[m - 1][n - 1] + if (char1 == char2) 0 else 1
                    val p3 = dp[m - 1][n] + 1

                    dp[m][n] = p1.coerceAtMost(p2).coerceAtMost(p3)
                }
            }

            return dp[word1.length][word2.length]
        }
    }

    expect {
        Solution().minDistance("horse", "ros")
    }
}

