package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            if (target < matrix[0][0] || target > matrix[matrix.size - 1][matrix[matrix.size - 1].size - 1]) {
                return false
            }

            val rowSize = matrix[0].size
            fun binarySearch(leftIndex: Int, rightIndex: Int): Boolean {
                if (leftIndex > rightIndex) {
                    return false
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                val midValue = matrix[midIndex / rowSize][midIndex % rowSize]
                return when {
                    midValue == target -> true
                    midValue < target -> binarySearch(midIndex + 1, rightIndex)
                    else -> binarySearch(leftIndex, midIndex - 1)
                }
            }

            return binarySearch(0, matrix.size * rowSize - 1)
        }
    }

    measureTimeMillis {
        println(
            Solution().searchMatrix(
                arrayOf(
                    intArrayOf(1, 3),
                ), 2
            )
        )
    }.also { println("Time cost: ${it}ms") }
}

