package p25xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteGreatestValue(grid: Array<IntArray>): Int {
            grid.forEach { it.sort() }

            return grid[0].indices.map { i ->
                grid.map { it[i] }.max()
            }.sum()
        }
    }

    measureTimeMillis {
        Solution().deleteGreatestValue(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

