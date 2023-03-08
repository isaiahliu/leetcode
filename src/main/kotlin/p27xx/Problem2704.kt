package p27xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxValue(grid: Array<IntArray>): Int {
            if (grid.isEmpty() || grid[0].isEmpty()) {
                return 0
            }

            for (i in 1 until grid.size) {
                grid[i][0] += grid[i - 1][0]
            }

            for (i in 1 until grid[0].size) {
                grid[0][i] += grid[0][i - 1]
            }

            for (i in 1 until grid.size) {
                val row = grid[i]

                for (j in 1 until row.size) {
                    grid[i][j] += grid[i - 1][j].coerceAtLeast(grid[i][j - 1])
                }
            }

            return grid[grid.size - 1][grid[0].size - 1]
        }
    }

    measureTimeMillis {
        Solution().maxValue(emptyArray()).also { println(it) }
    }
}

