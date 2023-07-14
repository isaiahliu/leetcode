package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumMoves(grid: Array<IntArray>): Int {
            val RIGHT = 0
            val DOWN = 1

            val visited = hashSetOf(0 to 1 to RIGHT)
            val tasks = visited.toMutableSet()

            var result = 0

            fun addTask(r: Int, c: Int, direction: Int) {
                val key = r to c to direction

                if (visited.add(key)) {
                    tasks.add(key)
                }
            }
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (p, direction) ->
                    val (r, c) = p

                    if (r == grid.lastIndex && c == grid[r].lastIndex && direction == RIGHT) {
                        return result
                    }

                    when (direction) {
                        RIGHT -> {
                            if (grid[r].getOrNull(c + 1) == 0) {
                                addTask(r, c + 1, RIGHT)
                            }

                            if (grid.getOrNull(r + 1)?.getOrNull(c - 1) == 0 && grid.getOrNull(r + 1)
                                    ?.getOrNull(c) == 0
                            ) {
                                addTask(r + 1, c - 1, DOWN)
                                addTask(r + 1, c, RIGHT)
                            }
                        }

                        DOWN -> {
                            if (grid.getOrNull(r + 1)?.getOrNull(c) == 0) {
                                addTask(r + 1, c, DOWN)
                            }

                            if (grid[r - 1].getOrNull(c + 1) == 0 && grid[r].getOrNull(c + 1) == 0) {
                                addTask(r - 1, c + 1, RIGHT)
                                addTask(r, c + 1, DOWN)
                            }
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().minimumMoves(
            arrayOf(
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
