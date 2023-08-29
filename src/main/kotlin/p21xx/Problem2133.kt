package p21xx

import util.expect

fun main() {
    class Solution {
        fun checkValid(matrix: Array<IntArray>): Boolean {
            val rows = Array(matrix.size) { hashSetOf<Int>() }
            val columns = Array(matrix.size) { hashSetOf<Int>() }

            matrix.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (!rows[r].add(num)) {
                        return false
                    }
                    if (!columns[c].add(num)) {
                        return false
                    }
                }
            }
            return true
        }
    }

    expect {
        Solution().checkValid(
            arrayOf()
        )
    }
}