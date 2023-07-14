package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numWays(steps: Int, arrLen: Int): Int {
            val m = 1000000007

            val dp = Array(steps + 1) { LongArray(arrLen.coerceAtMost(steps / 2 + 1)) }
            dp[0][0] = 1

            for (i in 1..steps) {
                val pre = dp[i - 1]
                val current = dp[i]

                repeat(pre.size) {
                    current[it] = arrayOf(it - 1, it, it + 1).mapNotNull { pre.getOrNull(it) }.sum() % m
                }
            }

            return dp.last()[0].toInt()
        }
    }

    measureTimeMillis {
        Solution().numWays(
            3, 2000
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
