package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTilings(n: Int): Int {
            val m = 1000000007

            val dp = Array((n + 1).coerceAtLeast(5)) {
                LongArray(3)
            }

            dp[0][0] = 1
            dp[1][0] = 1
            dp[2][0] = 2
            for (i in 3..n) {
                dp[i][0] = (dp[i - 1].sum() + dp[i - 2].sum()) % m
                dp[i][1] = (dp[i - 3].sum() * 2 + dp[i - 2][1]) % m
                dp[i][2] = ((dp.getOrNull(i - 4)?.sum() ?: 0) * 2 + dp[i - 2][2]) % m
            }

            return (dp[n].sum() % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().numTilings(
            3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}