package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumDeletions(s: String): Int {
            val dp = Array(s.length) {
                //0 -- a, 1 -- b
                IntArray(2)
            }

            dp[0][0] = 1
            dp[0][0] = 1
            dp[0][s[0] - 'a'] = 0

            for (i in 1 until dp.size) {
                val (a, b) = dp[i - 1]

                when (s[i]) {
                    'a' -> {
                        dp[i][0] = a
                        dp[i][1] = b + 1
                    }

                    'b' -> {
                        dp[i][0] = a + 1
                        dp[i][1] = a.coerceAtMost(b)
                    }
                }
            }

            return dp[dp.lastIndex].min()
        }
    }

    measureTimeMillis {
        Solution().minimumDeletions(
            "bbaaaaabb"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

