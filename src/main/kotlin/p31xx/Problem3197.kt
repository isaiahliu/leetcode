package p31xx

import util.expect
import kotlin.math.max
import kotlin.math.min

fun main() {
    class Solution {
        private fun minimumSum2(grid: Array<IntArray>, u: Int, d: Int, l: Int, r: Int): Int {
            var minI = grid.size
            var maxI = 0
            var minJ = grid[0].size
            var maxJ = 0
            for (i in u..d) {
                for (j in l..r) {
                    if (grid[i][j] == 1) {
                        minI = min(minI, i)
                        minJ = min(minJ, j)
                        maxI = max(maxI, i)
                        maxJ = max(maxJ, j)
                    }
                }
            }
            return if (minI <= maxI) (maxI - minI + 1) * (maxJ - minJ + 1) else Int.MAX_VALUE / 3
        }

        private fun rotate(vec: Array<IntArray>): Array<IntArray> {
            val n = vec.size
            val m = vec[0].size
            val ret = Array(m) { IntArray(n) }
            for (i in 0..<n) {
                for (j in 0..<m) {
                    ret[m - j - 1][i] = vec[i][j]
                }
            }
            return ret
        }

        private fun solve(grid: Array<IntArray>): Int {
            val n = grid.size
            val m = grid[0].size
            var res = n * m
            run {
                var i = 0
                while (i + 1 < n) {
                    var j = 0
                    while (j + 1 < m) {
                        res = min(
                            res, (minimumSum2(grid, 0, i, 0, m - 1)
                                    + minimumSum2(grid, i + 1, n - 1, 0, j)
                                    + minimumSum2(grid, i + 1, n - 1, j + 1, m - 1))
                        )
                        res = min(
                            res, (minimumSum2(grid, 0, i, 0, j)
                                    + minimumSum2(grid, 0, i, j + 1, m - 1)
                                    + minimumSum2(grid, i + 1, n - 1, 0, m - 1))
                        )
                        j++
                    }
                    i++
                }
            }
            var i = 0
            while (i + 2 < n) {
                var j = i + 1
                while (j + 1 < n) {
                    res = min(
                        res, (minimumSum2(grid, 0, i, 0, m - 1)
                                + minimumSum2(grid, i + 1, j, 0, m - 1)
                                + minimumSum2(grid, j + 1, n - 1, 0, m - 1))
                    )
                    j++
                }
                i++
            }
            return res
        }

        fun minimumSum(grid: Array<IntArray>): Int {
            val rgrid = rotate(grid)
            return min(solve(grid), solve(rgrid))
        }
    }

    expect {
        Solution().minimumSum(
            arrayOf()
        )
    }
}
