package p24xx

import util.expect

fun main() {
    class Solution {
        fun deleteString(s: String): Int {
            val sameLengths = Array(s.length) { i ->
                IntArray(s.length) { j ->
                    if (s[i] == s[j]) {
                        1
                    } else {
                        0
                    }
                }
            }

            for (i in s.lastIndex - 1 downTo 0) {
                for (j in s.lastIndex - 1 downTo 0) {
                    if (sameLengths[i][j] > 0) {
                        sameLengths[i][j] += sameLengths[i + 1][j + 1]
                    }
                }
            }

            val dp = IntArray(s.length) { 1 }

            for (index in dp.lastIndex - 1 downTo 0) {
                for (nextHead in index + 1 until dp.size) {
                    if (sameLengths[index][nextHead] >= nextHead - index) {
                        dp[index] = dp[index].coerceAtLeast(dp[nextHead] + 1)
                    }
                }
            }

            return dp[0]
        }
    }

    expect {
        Solution().deleteString(
            "aaabaab"
        )
    }
}