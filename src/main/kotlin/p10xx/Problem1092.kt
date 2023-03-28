package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestCommonSupersequence(str1: String, str2: String): String {
            val TOP_LEFT = 0
            val TOP = 1
            val LEFT = 2

            val defaultPair = ' ' to TOP_LEFT to 0
            val dp = Array(str1.length + 1) { r ->
                Array(str2.length + 1) { c ->
                    when {
                        r == 0 && c == 0 -> defaultPair
                        c == 0 -> str1[r - 1] to TOP to r
                        r == 0 -> str2[c - 1] to LEFT to c
                        else -> defaultPair
                    }
                }
            }

            str1.forEachIndexed { r, c1 ->
                str2.forEachIndexed { c, c2 ->
                    dp[r + 1][c + 1] = when {
                        c1 == c2 -> {
                            c1 to TOP_LEFT to dp[r][c].second + 1
                        }

                        dp[r][c + 1].second < dp[r + 1][c].second -> {
                            c1 to TOP to dp[r][c + 1].second + 1
                        }

                        else -> {
                            c2 to LEFT to dp[r + 1][c].second + 1
                        }
                    }
                }
            }

            val result = StringBuilder()

            var r = str1.length
            var c = str2.length

            while (true) {
                val (t, length) = dp[r][c]

                if (length == 0) {
                    break
                }

                val (ch, from) = t
                result.insert(0, ch)

                when (from) {
                    TOP_LEFT -> {
                        r--
                        c--
                    }

                    TOP -> r--
                    LEFT -> c--
                }
            }

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().shortestCommonSupersequence(
            "abac",
            "cab"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}