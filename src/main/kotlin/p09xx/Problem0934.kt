package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestBridge(grid: Array<IntArray>): Int {
            var r = 0
            var c = 0

            loop@ for ((rowIndex, row) in grid.withIndex()) {
                for ((columnIndex, num) in row.withIndex()) {
                    if (num > 0) {
                        r = rowIndex
                        c = columnIndex
                        break@loop
                    }
                }
            }

            val firstIsland = hashSetOf(r to c)
            val tasks = hashSetOf(r to c)

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        grid.getOrNull(it.first)?.getOrNull(it.second) == 1
                    }.forEach {
                        if (firstIsland.add(it)) {
                            tasks.add(it)
                        }
                    }
                }
            }

            var result = 0

            tasks.addAll(firstIsland)

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        grid.getOrNull(it.first)?.getOrNull(it.second) != null
                    }.forEach {
                        if (firstIsland.add(it)) {
                            if (grid[it.first][it.second] == 1) {
                                return result
                            } else {
                                tasks.add(it)
                            }
                        }
                    }
                }

                result++
            }

            return result
        }
    }
    measureTimeMillis {
        Solution().shortestBridge(
            arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}