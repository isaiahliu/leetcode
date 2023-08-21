package p18xx

import util.expect

fun main() {
    class Solution {
        fun findRotation(mat: Array<IntArray>, target: Array<IntArray>): Boolean {
            val n = mat.lastIndex

            val rotates = hashSetOf<(Int, Int) -> Int>(
                { r, c -> mat[r][c] },
                { r, c -> mat[n - c][r] },
                { r, c -> mat[n - r][n - c] },
                { r, c -> mat[c][n - r] }
            )

            for ((rowIndex, row) in target.withIndex()) {
                for ((columnIndex, num) in row.withIndex()) {
                    rotates.retainAll {
                        num == it(rowIndex, columnIndex)
                    }

                    if (rotates.isEmpty()) {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().findRotation(
            arrayOf(), arrayOf()
        )
    }
}
