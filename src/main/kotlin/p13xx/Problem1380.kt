package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
            val rowMin = IntArray(matrix.size) { Int.MAX_VALUE }
            val columnMax = IntArray(matrix[0].size) { Int.MIN_VALUE }

            matrix.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    rowMin[r] = rowMin[r].coerceAtMost(num)
                    columnMax[c] = columnMax[c].coerceAtLeast(num)
                }
            }

            return rowMin.intersect(columnMax.toSet()).toList()
        }
    }

    measureTimeMillis {
        Solution().luckyNumbers(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

