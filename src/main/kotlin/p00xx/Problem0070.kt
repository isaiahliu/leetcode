package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun climbStairs(n: Int): Int {
            return when (n) {
                1 -> 1
                2 -> 2
                else -> {
                    val dp = IntArray(n)
                    dp[0] = 1
                    dp[1] = 2

                    for (i in 2 until n) {
                        dp[i] = dp[i - 2] + dp[i - 1]
                    }

                    dp[n - 1]
                }
            }
        }
    }

    measureTimeMillis {
        println(Solution().climbStairs(3))
    }.also { println("Time cost: ${it}ms") }
}

