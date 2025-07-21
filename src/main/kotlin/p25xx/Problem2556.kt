package p25xx

import util.expect

fun main() {
    class Solution {
        fun isPossibleToCutPath(grid: Array<IntArray>): Boolean {
            val visited = hashSetOf<Pair<Int, Int>>()

            fun dfs(row: Int, column: Int, route: Set<Pair<Int, Int>>): Set<Pair<Int, Int>>? {
                if (row == grid.lastIndex && column == grid[0].lastIndex) {
                    return route
                }

                arrayOf(row to column + 1, row + 1 to column).forEach {
                    if (visited.add(it) && grid.getOrNull(it.first)?.getOrNull(it.second) == 1) {
                        dfs(it.first, it.second, route + (row to column))?.also {
                            return it
                        }
                    }
                }
                return null
            }

            dfs(0, 0, emptySet())?.also {
                visited.clear()
                visited += it
            } ?: return true

            return dfs(0, 0, emptySet()) == null
        }
    }

    expect {
        Solution().isPossibleToCutPath(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1),
                intArrayOf(1, 0, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 0, 1, 1),
                intArrayOf(0, 0, 0, 1, 1, 1)
            )
        )
    }
}