package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestPath(grid: Array<IntArray>, k: Int): Int {
            val m = grid.size
            val n = grid[0].size

            var result = 0

            val visited = hashSetOf(0 to 0 to k)
            val tasks = visited.toMutableSet()

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (pos, hammer) ->
                    if (pos.first == m - 1 && pos.second == n - 1) {
                        return result
                    }

                    arrayOf(
                        pos.first - 1 to pos.second,
                        pos.first + 1 to pos.second,
                        pos.first to pos.second - 1,
                        pos.first to pos.second + 1
                    ).filter {
                        it.first in 0 until m && it.second in 0 until n
                    }.forEach { (r, c) ->
                        var remainingHammer = hammer

                        if (grid[r][c] == 1) {
                            remainingHammer--
                        }

                        if (remainingHammer >= 0 && visited.add(r to c to remainingHammer)) {
                            tasks.add(r to c to remainingHammer)
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().shortestPath(
            arrayOf(
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
            ), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
