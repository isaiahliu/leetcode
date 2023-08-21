package p08xx

import util.expect

fun main() {
    class Solution {
        fun transpose(matrix: Array<IntArray>): Array<IntArray> {
            return Array(matrix[0].size) { c ->
                IntArray(matrix.size) { r ->
                    matrix[r][c]
                }
            }
        }
    }

    expect {
        Solution().transpose(
            arrayOf()
        )

    }
}