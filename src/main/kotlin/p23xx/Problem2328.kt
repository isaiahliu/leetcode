package p23xx

import util.expect

fun main() {
    class Solution {
        fun countPaths(grid: Array<IntArray>): Int {
            val cache = Array(grid.size) {
                IntArray(grid[it].size) {
                    -1
                }
            }

            val m = 1000000007
            fun dfs(row: Int, column: Int): Int {
                return if (cache[row][column] >= 0) {
                    cache[row][column]
                } else {
                    var result = 1L

                    val num = grid[row][column]
                    arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1).forEach { (dr, dc) ->
                        grid.getOrNull(row + dr)?.getOrNull(column + dc)?.takeIf { it > num }?.also {
                            result += dfs(row + dr, column + dc)
                            result %= m
                        }
                    }

                    cache[row][column] = result.toInt()
                    result.toInt()
                }
            }

            var result = 0L

            grid.indices.forEach { r ->
                grid[r].indices.forEach { c ->
                    result += dfs(r, c)
                    result %= m
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countPaths(
            arrayOf()
        )
    }
}