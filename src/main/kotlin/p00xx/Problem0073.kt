package p00xx

import util.expect

fun main() {
    class Solution {
        fun setZeroes(matrix: Array<IntArray>): Unit {
            val m = matrix.size
            val n = matrix[0].size

            var firstRowZero = false
            var firstColumnZero = false

            for (i in 0 until n) {
                if (matrix[0][i] == 0) {
                    firstRowZero = true
                    break
                }
            }

            for (i in 0 until m) {
                if (matrix[i][0] == 0) {
                    firstColumnZero = true
                    break
                }
            }

            for (rowIndex in 1 until m) {
                for (columnIndex in 1 until n) {
                    if (matrix[rowIndex][columnIndex] == 0) {
                        matrix[rowIndex][0] = 0
                        matrix[0][columnIndex] = 0
                    }
                }
            }

            for (i in 1 until m) {
                if (matrix[i][0] == 0) {
                    for (j in 1 until n) {
                        matrix[i][j] = 0
                    }
                }
            }

            for (i in 1 until n) {
                if (matrix[0][i] == 0) {
                    for (j in 1 until m) {
                        matrix[j][i] = 0
                    }
                }
            }

            if (firstRowZero) {
                for (i in 0 until n) {
                    matrix[0][i] = 0
                }
            }

            if (firstColumnZero) {
                for (i in 0 until m) {
                    matrix[i][0] = 0
                }
            }
        }
    }

    expect {
        Solution().setZeroes(
            arrayOf(
                intArrayOf(0, 1, 2, 0),
                intArrayOf(3, 4, 5, 2),
                intArrayOf(1, 3, 1, 5)
            )
        )
    }
}

