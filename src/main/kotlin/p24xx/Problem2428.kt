package p24xx

import util.expect

fun main() {
    class Solution {
        fun maxSum(grid: Array<IntArray>): Int {
            grid.forEach {
                for (i in 1 until it.size) {
                    it[i] += it[i - 1]
                }
            }

            var result = 0

            for (r in 1 until grid.lastIndex) {
                val row = grid[r]
                for (c in 1 until row.lastIndex) {
                    result = result.coerceAtLeast(
                        row[c] - row[c - 1] + grid[r - 1][c + 1] + grid[r + 1][c + 1] - grid[r - 1].getOrElse(c - 2) { 0 } - grid[r + 1].getOrElse(
                            c - 2
                        ) { 0 })
                }
            }

            return result
        }
    }

    expect {
        Solution().maxSum(
            arrayOf()
        )
    }
}