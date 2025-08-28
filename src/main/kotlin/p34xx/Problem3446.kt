package p34xx

import util.expect

fun main() {
    class Solution {
        fun sortMatrix(grid: Array<IntArray>): Array<IntArray> {
            return grid.indices.flatMap { r ->
                grid[r].indices.map { r to it }
            }.groupBy { (it.first - it.second) * 2 + 1 }.flatMap { (diff, positions) ->
                positions.sortedBy { (r, c) ->
                    grid[r][c] * -diff
                }.let { sorted ->
                    positions.indices.map { positions[it] to grid[sorted[it].first][sorted[it].second] }
                }
            }.toMap().let {
                Array(grid.size) { r ->
                    IntArray(grid[r].size) { c ->
                        it[r to c] ?: 0
                    }
                }
            }
        }
    }

    expect {
        Solution().sortMatrix(
            arrayOf()
        )
    }
}
