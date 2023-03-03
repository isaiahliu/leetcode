package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun integerBreak(n: Int): Int {
            val dp = IntArray(n + 1)
            dp[1] = 1

            for (i in 2..n) {
                for (j in 1..i / 2) {
                    dp[i] = dp[i].coerceAtLeast(dp[j].coerceAtLeast(j) * dp[i - j].coerceAtLeast(i - j))
                }
            }

            return dp[n]
        }
    }

    measureTimeMillis {
        Solution().integerBreak(
            10
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

