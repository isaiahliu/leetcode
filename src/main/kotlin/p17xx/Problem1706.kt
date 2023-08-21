package p17xx

import util.expect

fun main() {
    class Solution {
        fun findBall(grid: Array<IntArray>): IntArray {
            val dp = Array(grid.size) {
                IntArray(grid[it].size) { -1 }
            }

            for (rowIndex in dp.lastIndex downTo 0) {
                val row = grid[rowIndex]
                val dpRow = dp[rowIndex]
                val nextDpRow = dp.getOrNull(rowIndex + 1)

                row.forEachIndexed { index, d ->
                    if (row.getOrNull(index + d) == d) {
                        dpRow[index] = nextDpRow?.get(index + d) ?: (index + d)
                    }
                }
            }

            return dp[0]
        }
    }

    expect {
        Solution().findBall(
            arrayOf(
                intArrayOf(1, 1, 1, -1, -1),
                intArrayOf(1, 1, 1, -1, -1),
                intArrayOf(-1, -1, -1, 1, 1),
                intArrayOf(1, 1, 1, 1, -1),
                intArrayOf(-1, -1, -1, -1, -1)
            )
        ).toList()
    }
}
