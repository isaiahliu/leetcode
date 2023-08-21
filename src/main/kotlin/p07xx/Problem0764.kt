package p07xx

import util.expect

fun main() {
    class Solution {
        fun orderOfLargestPlusSign(n: Int, mines: Array<IntArray>): Int {
            var result = 0

            val mineSet = mines.map { it[0] to it[1] }.toSet()

            val sizes = Array(n) { r ->
                Array(n) { c ->
                    IntArray(4) {
                        if (r to c in mineSet) {
                            0
                        } else {
                            result = 1
                            1
                        }
                    }
                }
            }


            for (r in 1 until n - 1) {
                for (c in 1 until n - 1) {
                    if (sizes[r][c][0] != 0) {
                        sizes[r][c][0] += sizes[r - 1][c][0]
                        sizes[r][c][1] += sizes[r][c - 1][1]
                    }
                }
            }

            for (r in n - 2 downTo 1) {
                for (c in n - 2 downTo 1) {
                    if (sizes[r][c][0] != 0) {
                        sizes[r][c][2] += sizes[r + 1][c][2]
                        sizes[r][c][3] += sizes[r][c + 1][3]

                        result = result.coerceAtLeast(sizes[r][c].min())
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().orderOfLargestPlusSign(
            2, arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1),
                intArrayOf(1, 0),
            )
        )
    }
}