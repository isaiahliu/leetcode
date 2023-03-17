package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun stoneGameII(piles: IntArray): Int {
            if (piles.size == 1) {
                return piles[0]
            }

            val dp = Array(piles.size + 1) { IntArray(piles.size) }

            var sum = 0

            for (index in dp.size - 2 downTo 0) {
                sum += piles[index]

                for (m in 1 until piles.size) {
                    for (c in index + 1..(index + m * 2).coerceAtMost(dp.lastIndex)) {
                        dp[index][m] = dp[index][m].coerceAtLeast(
                            sum - dp[c][m.coerceAtLeast(c - index).coerceAtMost(piles.lastIndex)]
                        )
                    }
                }
            }

            return dp[0][1]
        }
    }

    measureTimeMillis {
        println(Solution().stoneGameII(intArrayOf(1)))
    }.also { println("Time cost: ${it}ms") }
}
