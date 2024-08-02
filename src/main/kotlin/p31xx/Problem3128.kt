package p31xx

import util.expect

fun main() {
    class Solution {
        fun numberOfRightTriangles(grid: Array<IntArray>): Long {
            val n = grid.size
            val m = grid[0].size
            val col = IntArray(m)
            for (i in 0 until n) {
                for (j in 0 until m) {
                    col[j] += grid[i][j]
                }
            }
            var result = 0L
            for (i in 0 until n) {
                val row = grid[i].sum()
                for (j in 0 until m) {
                    if (grid[i][j] == 1) {
                        result += ((row - 1) * (col[j] - 1)).toLong()
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().numberOfRightTriangles(
            arrayOf()
        )
    }
}
