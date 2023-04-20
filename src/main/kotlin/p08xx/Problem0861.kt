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

            var bitPos = grid[0].lastIndex
            var result = grid.size * (1 shl (bitPos--))

            for (i in 1 until grid[0].size) {
                val count = grid.map { it[i] }.groupingBy { it }.eachCount().values.max()

                result += count * (1 shl (bitPos--))
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().matrixScore(
            arrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}