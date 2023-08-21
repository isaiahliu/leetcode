package p00xx

import util.expect

fun main() {
    class Solution {
        fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
            val m = obstacleGrid.size
            val n = obstacleGrid[0].size
            val dp = Array(m) { IntArray(n) }

            dp[0][0] = 1 - obstacleGrid[0][0]

            repeat(m) { row ->
                repeat(n) { column ->
                    if (obstacleGrid[row][column] == 0) {
                        dp.getOrNull(row - 1)?.getOrNull(column)?.also {
                            dp[row][column] += it
                        }

                        dp.getOrNull(row)?.getOrNull(column - 1)?.also {
                            dp[row][column] += it
                        }
                    }
                }
            }

            return dp[m - 1][n - 1]
        }
    }

    expect {
        Solution().uniquePathsWithObstacles(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)
            )
        )
    }
}

