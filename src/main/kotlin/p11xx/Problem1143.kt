package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestCommonSubsequence(text1: String, text2: String): Int {
            val dp = Array(text1.length) {
                IntArray(text2.length)
            }

            var match = 0
            text1.forEachIndexed { index, c1 ->
                if (text2[0] == c1) {
                    match = 1
                }

                dp[index][0] = match
            }

            match = 0
            text2.forEachIndexed { index, c2 ->
                if (text1[0] == c2) {
                    match = 1
                }

                dp[0][index] = match
            }

            for (index1 in 1 until text1.length) {
                for (index2 in 1 until text2.length) {
                    dp[index1][index2] = dp[index1 - 1][index2].coerceAtLeast(dp[index1][index2 - 1])

                    if (text1[index1] == text2[index2]) {
                        dp[index1][index2] = dp[index1][index2].coerceAtLeast(dp[index1 - 1][index2 - 1] + 1)
                    }
                }
            }

            return dp.last().last()
        }
    }

    measureTimeMillis {
        Solution().longestCommonSubsequence(
            "abcde",
            "ace"
        ).also { println(it) }
    }
}

