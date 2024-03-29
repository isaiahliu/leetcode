package p08xx

import util.expect

fun main() {
    class Solution {
        fun new21Game(n: Int, k: Int, maxPts: Int): Double {
            val r = 1.0 / maxPts

            val dp = DoubleArray(n + 1)

            dp[0] = 1.0

            for (i in 1..n) {
                for (last in (i - maxPts).coerceAtLeast(0) until i.coerceAtMost(k)) {
                    dp[i] += dp[last] * r
                }
            }

            return (k..n).map { dp[it] }.sum()
        }
    }

    expect {
        Solution().new21Game(
            6, 1, 10
        )
    }
}