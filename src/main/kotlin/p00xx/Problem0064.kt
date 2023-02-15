package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minPathSum(grid: Array<IntArray>): Int {
            val m = grid.size
            val n = grid[0].size
            val dp = Array(m) { IntArray(n) }

            repeat(m) { row ->
                repeat(n) { column ->
                    dp[row][column] = (arrayOf(row - 1 to column, row to column - 1).mapNotNull { (r, c) ->
                        dp.getOrNull(r)?.getOrNull(c)
                    }.minOrNull() ?: 0) + grid[row][column]
                }
            }

            return dp[m - 1][n - 1]
        }
    }

    measureTimeMillis {
        println(
            Solution().minPathSum(
                arrayOf(
                    intArrayOf(1, 3, 1),
                    intArrayOf(1, 5, 1),
                    intArrayOf(4, 2, 1)
                )
            )
        )
    }.also { println("Time cost: ${it}ms") }
}

