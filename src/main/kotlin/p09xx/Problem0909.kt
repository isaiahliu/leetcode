package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun snakesAndLadders(board: Array<IntArray>): Int {
            val n = board.size

            fun Int.toGrid(): Pair<Int, Int> {
                val r = n - (this - 1) / n - 1

                var c = (this - 1) % n
                if ((this - 1) / n % 2 == 1) {
                    c = n - c - 1
                }

                return r to c
            }

            val ladders = mutableMapOf<Int, Int>()

            repeat(n * n) {
                val (r, c) = (it + 1).toGrid()

                if (board[r][c] >= 0) {
                    ladders[it + 1] = board[r][c]
                }
            }

            val visited = hashSetOf(1)
            val tasks = hashSetOf(1)

            var step = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { pos ->
                    if (pos == n * n) {
                        return step
                    }
                    repeat(6) {
                        val newPos = (pos + it + 1).takeIf { it <= n * n }?.let {
                            ladders[it] ?: it
                        } ?: return@repeat

                        if (visited.add(newPos)) {
                            tasks.add(newPos)
                        }

                    }
                }
                step++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().snakesAndLadders(
            arrayOf(
                intArrayOf(-1, 11, 6, -1),
                intArrayOf(-1, 15, 16, -1),
                intArrayOf(-1, 7, -1, 8),
                intArrayOf(-1, -1, -1, 8)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}