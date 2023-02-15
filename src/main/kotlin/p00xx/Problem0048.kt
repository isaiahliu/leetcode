package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rotate(matrix: Array<IntArray>): Unit {
            val size = matrix.size
            for (row in 0 until size / 2) {
                for (column in row until size - row - 1) {
                    val t = matrix[row][column]

                    matrix[row][column] = matrix[size - column - 1][row]
                    matrix[size - column - 1][row] = matrix[size - row - 1][size - column - 1]
                    matrix[size - row - 1][size - column - 1] = matrix[column][size - row - 1]
                    matrix[column][size - row - 1] = t
                }
            }
            val a = 1
        }
    }

    measureTimeMillis {
        println(
            Solution().rotate(
                arrayOf(
                    intArrayOf(5, 1, 9, 11),
                    intArrayOf(2, 4, 8, 10),
                    intArrayOf(13, 3, 6, 7),
                    intArrayOf(15, 14, 12, 16),
                )
            )
        )
    }.also { println("Time cost: ${it}ms") }
}


