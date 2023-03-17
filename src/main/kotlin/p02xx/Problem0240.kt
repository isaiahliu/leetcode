package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            var rightIndex = matrix[0].lastIndex

            fun IntArray.binarySearch(startIndex: Int, endIndex: Int): Boolean {
                if (startIndex > endIndex) {
                    return false
                }

                val midIndex = startIndex + (endIndex - startIndex) / 2
                val midNum = this[midIndex]

                return when {
                    midNum < target -> {
                        rightIndex = midIndex
                        binarySearch(midIndex + 1, endIndex)
                    }

                    midNum > target -> {
                        binarySearch(startIndex, midIndex - 1)
                    }

                    else -> {
                        true
                    }
                }

            }

            for (row in matrix) {
                if (rightIndex < 0) {
                    break
                }

                if (row.binarySearch(0, rightIndex.also { rightIndex = -1 })) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().searchMatrix(
            arrayOf(
                intArrayOf(1, 4, 7, 11, 15),
                intArrayOf(2, 5, 8, 12, 19),
                intArrayOf(3, 6, 9, 16, 22),
                intArrayOf(10, 13, 14, 17, 24),
                intArrayOf(18, 21, 23, 26, 30)
            ), 5
        ).also { println(it) }
    }
}

