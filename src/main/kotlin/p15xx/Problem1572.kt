package p15xx

import util.expect

fun main() {
    class Solution {
        fun diagonalSum(mat: Array<IntArray>): Int {
            var result = 0

            mat.forEachIndexed { index, row ->
                val op = mat.lastIndex - index

                result += row[index]

                if (op != index) {
                    result += row[op]
                }
            }

            return result
        }
    }

    expect {
        Solution().diagonalSum(
            arrayOf()
        )
    }
}

