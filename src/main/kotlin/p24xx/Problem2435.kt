package p24xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPaths(grid: Array<IntArray>, k: Int): Int {
            val dp = Array(grid.size) {
                Array(grid[it].size) {
                    hashMapOf<Int, Int>()
                }
            }

            dp[0][0][grid[0][0] % k] = 1
            val m = 1000000007

            dp.forEachIndexed { r, row ->
                row.forEachIndexed { c, p ->
                    arrayOf(dp.getOrNull(r - 1)?.get(c), dp[r].getOrNull(c - 1)).forEach {
                        it?.forEach { (rem, count) ->
                            p[(rem + grid[r][c]) % k] =
                                (((p[(rem + grid[r][c]) % k] ?: 0) + count.toLong()) % m).toInt()
                        }
                    }
                }
            }

            return dp.last().last()[0] ?: 0
        }
    }

    expect {
        Solution().numberOfPaths(
            arrayOf(), 1
        )
    }
}