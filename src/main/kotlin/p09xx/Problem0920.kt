package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numMusicPlaylists(n: Int, goal: Int, k: Int): Int {
            val m = 1000000007L

            val dp = Array(goal + 1) { LongArray(n + 1) }
            dp[0][0] = 1

            for (length in 1..goal) {
                for (songs in 1..n) {
                    dp[length][songs] = dp[length - 1][songs - 1]
                    dp[length][songs] += dp[length - 1][songs] * (songs - k).coerceAtLeast(0)
                    dp[length][songs] %= m
                }
            }

            var result = dp[goal][n]

            repeat(n) {
                result *= (it + 1)
                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().numMusicPlaylists(
            3, 3, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}