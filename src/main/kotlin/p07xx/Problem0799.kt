package p07xx

import util.expect

fun main() {
    class Solution {
        fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
            val glasses = Array(query_row + 2) {
                DoubleArray(it + 1)
            }

            glasses[0][0] = poured.toDouble()

            for (r in 0..query_row) {
                glasses[r].forEachIndexed { c, d ->
                    if (d > 1) {
                        glasses[r][c] = 1.0

                        ((d - 1) / 2).also {
                            glasses[r + 1][c] += it
                            glasses[r + 1][c + 1] += it
                        }
                    }
                }
            }

            return glasses[query_row][query_glass]
        }
    }

    expect {
        Solution().champagneTower(
            100000009,
            33,
            17
        )
    }
}