package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numMagicSquaresInside(grid: Array<IntArray>): Int {
            var result = 0
            for (r in 1 until grid.size - 1) {
                for (c in 1 until grid[r].size - 1) {
                    if (grid[r][c] == 5) {
                        val set = (1..9).toMutableSet()
                        set.remove(5)

                        fun check(num1: Int, num2: Int): Boolean {
                            set.remove(num1)
                            set.remove(num2)

                            return num1 + num2 == 10
                        }

                        if (!check(grid[r - 1][c], grid[r + 1][c])) {
                            continue
                        }

                        if (!check(grid[r][c - 1], grid[r][c + 1])) {
                            continue
                        }

                        if (!check(grid[r - 1][c - 1], grid[r + 1][c + 1])) {
                            continue
                        }

                        if (!check(grid[r - 1][c + 1], grid[r + 1][c - 1])) {
                            continue
                        }

                        if (grid[r - 1][c - 1] + grid[r - 1][c] + grid[r - 1][c + 1] != 15) {
                            continue
                        }

                        if (grid[r - 1][c - 1] + grid[r][c - 1] + grid[r + 1][c - 1] != 15) {
                            continue
                        }

                        if (set.isEmpty()) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numMagicSquaresInside(
            arrayOf(
                intArrayOf(8, 0, 7, 6, 0, 9, 2),
                intArrayOf(4, 5, 6, 8, 5, 2, 9),
                intArrayOf(10, 5, 6, 1, 10, 4, 1),
                intArrayOf(3, 7, 11, 5, 2, 5, 8),
                intArrayOf(8, 9, 4, 0, 4, 9, 2),
                intArrayOf(1, 8, 2, 5, 3, 5, 7),
                intArrayOf(6, 2, 9, 0, 8, 1, 6)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}