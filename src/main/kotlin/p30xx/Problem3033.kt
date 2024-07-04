package p30xx

import util.expect

fun main() {
    class Solution {
        fun modifiedMatrix(matrix: Array<IntArray>): Array<IntArray> {
            val columns = IntArray(matrix[0].size) { -1 }

            matrix.forEach { row ->
                row.forEachIndexed { c, num ->
                    columns[c] = maxOf(columns[c], num)
                }
            }

            return Array(matrix.size) { r ->
                IntArray(matrix[r].size) { c ->
                    matrix[r][c].takeIf { it >= 0 } ?: columns[c]
                }
            }
        }
    }

    expect {
        Solution().modifiedMatrix(
            arrayOf()
        )
    }
}
