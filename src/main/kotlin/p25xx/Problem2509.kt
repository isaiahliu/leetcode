package p25xx

import util.expect

fun main() {
    class Solution {
        fun cycleLengthQueries(n: Int, queries: Array<IntArray>): IntArray {
            fun findSize(a: Int, b: Int): Int {
                return when {
                    a > b -> findSize(b, a)
                    a == b -> 1
                    else -> {
                        findSize(a, b / 2) + 1
                    }
                }
            }

            return queries.map { (a, b) ->
                findSize(a, b)
            }.toIntArray()
        }
    }

    expect {
        Solution().cycleLengthQueries(
            3, arrayOf(
                intArrayOf(5, 3),
                intArrayOf(4, 7),
                intArrayOf(2, 3),
            )
        )
    }
}