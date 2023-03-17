package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findSubstringInWraproundString(s: String): Int {
            val dp = IntArray(26)
            dp[s[s.lastIndex] - 'a'] = 1

            var t = 1
            for (i in s.length - 2 downTo 0) {
                if ((s[i + 1] - s[i] + 26) % 26 == 1) {
                    t++
                } else {
                    t = 1
                }

                dp[s[i] - 'a'] = dp[s[i] - 'a'].coerceAtLeast(t)
            }

            return dp.sum()
        }
    }

    measureTimeMillis {
        Solution().findSubstringInWraproundString(
            "zab"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}