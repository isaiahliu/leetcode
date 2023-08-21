package p16xx

import util.expect

fun main() {
    class Solution {
        fun countVowelStrings(n: Int): Int {
            val dp = IntArray(5)
            dp[0] = 1

            repeat(n) {
                repeat(4) {
                    dp[it + 1] += dp[it]
                }
            }

            return dp.sum()
        }
    }

    expect {
        Solution().countVowelStrings(
            33
        )
    }
}