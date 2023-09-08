package p23xx

import util.expect

fun main() {
    class Solution {
        fun maximumRows(matrix: Array<IntArray>, numSelect: Int): Int {
            val all = matrix.map { it.mapIndexed { index, i -> if (i == 1) 1 shl index else 0 }.sum() }

            var result = 0
            repeat(1 shl matrix[0].size) { status ->
                if (status.countOneBits() == numSelect) {
                    result = result.coerceAtLeast(all.count { it and status == it })
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumRows(
            arrayOf(), 5
        )
    }
}