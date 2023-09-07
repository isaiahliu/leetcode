package p23xx

import util.expect

fun main() {
    class Solution {
        fun checkXMatrix(grid: Array<IntArray>): Boolean {
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (r == c || grid.lastIndex - r == c) {
                        if (num == 0) {
                            return false
                        }
                    } else {
                        if (num != 0) {
                            return false
                        }
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().checkXMatrix(
            arrayOf()
        )
    }
}