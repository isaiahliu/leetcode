package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun fib(n: Int): Int {
            val dp = IntArray(n + 1) { it }

            for (i in 2..n) {
                dp[i] = dp[i - 2] + dp[i - 1]
            }

            return dp[n]
        }
    }

    measureTimeMillis {
        Solution().fib(
            4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}