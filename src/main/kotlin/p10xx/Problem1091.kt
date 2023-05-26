package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
            if (grid[0][0] != 0) {
                return -1
            }

            val tasks = hashSetOf(0 to 0)

            var result = 1
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    if (r == grid.lastIndex && c == grid[r].lastIndex) {
                        return result
                    }

                    arrayOf(
                        r - 1 to c - 1,
                        r - 1 to c,
                        r - 1 to c + 1,
                        r to c - 1,
                        r to c + 1,
                        r + 1 to c - 1,
                        r + 1 to c,
                        r + 1 to c + 1,
                    ).filter { grid.getOrNull(it.first)?.getOrNull(it.second) == 0 }.forEach {
                        tasks.add(it)
                        grid[it.first][it.second] = 1
                    }
                }

                result++
            }
            return -1
        }
    }

    measureTimeMillis {
        Solution().shortestPathBinaryMatrix(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
