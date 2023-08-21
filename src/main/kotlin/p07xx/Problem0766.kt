package p07xx

import util.expect

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

    expect {
        Solution().isToeplitzMatrix(
            arrayOf()
        )
    }
}