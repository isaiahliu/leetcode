package p20xx

import util.expect

fun main() {
    class Solution {
        fun countPyramids(grid: Array<IntArray>): Int {
            val sums = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    intArrayOf(grid[r][c], grid[r][c])
                }
            }

            sums.forEach {
                for (i in 1 until it.size) {
                    if (it[i][0] > 0) {
                        it[i][0] += it[i - 1][0]
                    }
                    if (it[it.lastIndex - i][1] > 0) {
                        it[it.lastIndex - i][1] += it[it.lastIndex - i + 1][1]
                    }
                }
            }

            var result = 0
            for (c in 1 until grid[0].lastIndex) {
                var topDownSize = 0
                var downTopSize = 0

                for (index in grid.indices) {
                    topDownSize = sums[index][c].min().coerceAtMost(topDownSize + 1)
                    result += (topDownSize - 1).coerceAtLeast(0)

                    downTopSize = sums[grid.lastIndex - index][c].min().coerceAtMost(downTopSize + 1)
                    result += (downTopSize - 1).coerceAtLeast(0)
                }
            }

            return result
        }
    }

    expect(13) {
        Solution().countPyramids(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 0),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(0, 1, 0, 0, 1),
            )
        )
    }
}