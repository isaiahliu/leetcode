package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun colorBorder(grid: Array<IntArray>, row: Int, col: Int, color: Int): Array<IntArray> {
            val originalColor = grid[row][col]

            if (originalColor != color) {
                grid[row][col] = color

                val visited = hashSetOf(row to col)
                val edges = hashSetOf<Pair<Int, Int>>()
                val tasks = hashSetOf(row to col)

                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        val neighbors = arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                            grid.getOrNull(it.first)?.getOrNull(it.second) == originalColor
                        }

                        if (neighbors.size < 4) {
                            edges.add(r to c)
                        }

                        neighbors.forEach {
                            if (visited.add(it))
                                tasks.add(it)
                        }
                    }
                }
                edges.forEach { (r, c) ->
                    grid[r][c] = color
                }
            }

            return grid
        }
    }

    measureTimeMillis {
        Solution().colorBorder(
            arrayOf(), 1, 2, 3
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
