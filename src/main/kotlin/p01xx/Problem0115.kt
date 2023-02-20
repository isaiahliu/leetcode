package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numDistinct(s: String, t: String): Int {
            if (s.length < t.length) {
                return 0
            }

            if (s.length == t.length) {
                return if (s == t) 1 else 0
            }

            val dp = Array(s.length) {
                IntArray(t.length + 1)
            }

            repeat(s.length) {
                dp[it][0] = 1
            }

            dp[0][1] = if (s[0] == t[0]) 1 else 0

            for (r in 1 until s.length) {
                for (c in 1 until t.length + 1) {
                    val left = s[r]
                    val right = t[c - 1]

                    dp[r][c] = dp[r - 1][c] + if (left == right) dp[r - 1][c - 1] else 0
                }
            }

            return dp[s.length - 1][t.length]
        }
    }

    measureTimeMillis {
        Solution().numDistinct(
            "bbb", "bb"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

