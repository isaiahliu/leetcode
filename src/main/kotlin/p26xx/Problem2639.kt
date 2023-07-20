package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findColumnWidth(grid: Array<IntArray>): IntArray {
            val result = IntArray(grid.map { it.size }.max())

            grid.forEach {
                it.forEachIndexed { index, i ->
                    result[index] = result[index].coerceAtLeast(i.toString().length)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findColumnWidth(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
