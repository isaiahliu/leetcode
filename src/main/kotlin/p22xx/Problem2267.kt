package p22xx

import util.expect

fun main() {
    class Solution {
        fun hasValidPath(grid: Array<CharArray>): Boolean {
            if (grid[0][0] == ')' || grid.last().last() == '(') {
                return false
            }

            val size = grid.size + grid[0].size - 1
            if (size % 2 == 1) {
                return false
            }

            val cache = Array(grid.size) {
                Array(grid[it].size) {
                    BooleanArray(size / 2 + 1)
                }
            }

            fun dfs(row: Int, column: Int, depth: Int): Boolean {
                return when {
                    row !in grid.indices || column !in grid[0].indices -> {
                        false
                    }

                    depth < 0 -> {
                        false
                    }

                    depth * 2 > size -> {
                        false
                    }

                    cache[row][column][depth] -> {
                        false
                    }

                    row == grid.lastIndex && column == grid[0].lastIndex -> {
                        depth == 1
                    }

                    else -> {
                        cache[row][column][depth] = true
                        var newDepth = depth
                        when (grid[row][column]) {
                            '(' -> newDepth++
                            ')' -> newDepth--
                        }

                        arrayOf(row + 1 to column, row to column + 1).any {
                            dfs(it.first, it.second, newDepth)
                        }
                    }
                }
            }

            return dfs(0, 0, 0)
        }
    }

    expect {
        Solution().hasValidPath(
            arrayOf(
                charArrayOf('(', '(', '('),
                charArrayOf(')', '(', ')'),
                charArrayOf('(', '(', ')'),
                charArrayOf('(', '(', ')')
            )
        )
    }
}