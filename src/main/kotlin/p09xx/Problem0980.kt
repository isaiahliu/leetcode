package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun uniquePathsIII(grid: Array<IntArray>): Int {
            var start = 0 to 0
            var end = 0 to 0
            var emptyCount = 0

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, i ->
                    when (i) {
                        1 -> {
                            start = rowIndex to columnIndex
                        }

                        2 -> {
                            end = rowIndex to columnIndex
                        }

                        0 -> {
                            emptyCount++
                        }
                    }
                }
            }
            val movable = arrayOf(0, 2)

            var result = 0
            fun dfs(pos: Pair<Int, Int>, route: Set<Pair<Int, Int>>) {
                if (pos == end) {
                    if (route.size == emptyCount + 1) {
                        result++
                    }
                    return
                }

                val (row, column) = pos

                arrayOf(row - 1 to column, row + 1 to column, row to column - 1, row to column + 1).forEach { (r, c) ->
                    if ((grid.getOrNull(r)?.getOrNull(c) ?: -1) in movable && r to c !in route) {
                        dfs(r to c, route + (r to c))
                    }
                }
            }

            dfs(start, emptySet())

            return result
        }
    }

    measureTimeMillis {
        Solution().uniquePathsIII(
            arrayOf(
                intArrayOf(1, 0, 0, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 0, 2, -1)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
