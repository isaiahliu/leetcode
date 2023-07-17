package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countNegatives(grid: Array<IntArray>): Int {
            val n = grid[0].size
            var columnIndex = n - 1

            var result = 0
            grid.forEach {
                while (columnIndex >= 0 && it[columnIndex] < 0) {
                    columnIndex--
                }

                result += n - columnIndex - 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countNegatives(
            arrayOf(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

