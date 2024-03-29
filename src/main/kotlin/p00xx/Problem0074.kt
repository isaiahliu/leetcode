package p00xx

import util.expect

fun main() {
    class Solution {
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            if (target < matrix[0][0] || target > matrix[matrix.lastIndex][matrix[matrix.lastIndex].lastIndex]) {
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

    expect {
        Solution().searchMatrix(
            arrayOf(
                intArrayOf(1, 3),
            ), 2
        )
    }
}

