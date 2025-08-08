package p33xx

import util.expect

fun main() {
    class Solution {
        fun maxCollectedFruits(fruits: Array<IntArray>): Int {
            val dp = Array(fruits.size) {
                IntArray(fruits[it].size)
            }

            dp[fruits.lastIndex][0] = fruits[fruits.lastIndex][0]
            dp[0][fruits.lastIndex] = fruits[0][fruits.lastIndex]

            for (r in 1 until fruits.size) {
                for (c in maxOf(r + 1, fruits.lastIndex - r) until fruits.size) {
                    dp[r][c] = fruits[r][c] + maxOf(dp[r - 1][c - 1], dp[r - 1][c], dp[r - 1].getOrNull(c + 1) ?: 0)
                    dp[c][r] = fruits[c][r] + maxOf(dp[c - 1][r - 1], dp[c][r - 1], dp.getOrNull(c + 1)?.get(r - 1) ?: 0)
                }
            }

            return fruits.indices.sumOf { fruits[it][it] } + dp[fruits.lastIndex][fruits.lastIndex - 1] + dp[fruits.lastIndex - 1][fruits.lastIndex]
        }
    }

    expect {
        Solution().maxCollectedFruits(
            arrayOf(
                intArrayOf(16, 3, 11, 14, 14),
                intArrayOf(3, 0, 10, 13, 14),
                intArrayOf(7, 18, 8, 7, 18),
                intArrayOf(7, 8, 5, 7, 5),
                intArrayOf(0, 14, 8, 1, 0),
            )
        )
    }
}
