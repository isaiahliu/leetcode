package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun matrixScore(grid: Array<IntArray>): Int {
            grid.forEach {
                if (it[0] == 0) {
                    it.forEachIndexed { index, num ->
                        it[index] = 1 - num
                    }
                }
            }

            for (i in 1 until grid.size) {
                for (j in grid[i].indices) {
                    grid[i][j] += grid[i - 1][j]
                }
            }

            return grid[grid.lastIndex].mapIndexed { index, i ->
                i.coerceAtLeast(grid.size - i) * (1 shl (grid[0].lastIndex - index))
            }.sum()
        }
    }

    measureTimeMillis {
        Solution().matrixScore(
            arrayOf(intArrayOf(0))
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}