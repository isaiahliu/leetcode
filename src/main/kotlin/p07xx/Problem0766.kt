package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
            for (r in 1 until matrix.size) {
                val row = matrix[r]
                for (c in 1 until row.size) {
                    if (row[c] != matrix[r - 1][c - 1]) {
                        return false
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isToeplitzMatrix(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}