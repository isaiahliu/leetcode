package p34xx

import util.expect

fun main() {
    class Solution {
        fun sortMatrix(grid: Array<IntArray>): Array<IntArray> {
            return grid.indices.flatMap { r ->
                grid[r].indices.map { r to it }
            }.groupBy { it.first - it.second }.flatMap { (diff, positions) ->
                val d = diff * 2 + 1

                val sorted = positions.sortedWith { (r1, c1), (r2, c2) ->
                    (grid[r2][c2] - grid[r1][c1]) * d
                }

                positions.indices.map { positions[it] to sorted[it] }
            }.toMap().let {
                Array(grid.size) { r ->
                    IntArray(grid[r].size) { c ->
                        it[r to c]?.let { (tr, tc) ->
                            grid[tr][tc]
                        } ?: 0
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
