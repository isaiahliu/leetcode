package p00xx

import util.expect

fun main() {
    class Solution {
        fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            if (matrix.isEmpty() || matrix[0].isEmpty()) {
                return emptyList()
            }

            val up = 0
            val right = 1
            val down = 2
            val left = 3

            var rowIndex = 0
            var columnIndex = 0

            var minRowIndex = 0
            var maxRowIndex = matrix.lastIndex
            var minColumnIndex = 0
            var maxColumnIndex = matrix[0].lastIndex

            val result = arrayListOf(matrix[0][0])
            var direction = right

            while (minRowIndex <= maxRowIndex && minColumnIndex <= maxColumnIndex) {
                when (direction % 4) {
                    up -> {
                        repeat(rowIndex - minRowIndex) {
                            rowIndex--
                            result.add(matrix[rowIndex][columnIndex])
                        }

                        minColumnIndex++
                    }

                    right -> {
                        repeat(maxColumnIndex - columnIndex) {
                            columnIndex++
                            result.add(matrix[rowIndex][columnIndex])
                        }

                        minRowIndex++
                    }

                    down -> {
                        repeat(maxRowIndex - rowIndex) {
                            rowIndex++
                            result.add(matrix[rowIndex][columnIndex])
                        }

                        maxColumnIndex--
                    }

                    left -> {
                        repeat(columnIndex - minColumnIndex) {
                            columnIndex--
                            result.add(matrix[rowIndex][columnIndex])
                        }

                        maxRowIndex--
                    }
                }

                direction++
            }

            return result
        }
    }

    expect {
        Solution().spiralOrder(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12)
            )
        )
    }
}


