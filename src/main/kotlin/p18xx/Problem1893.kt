package p18xx

import util.expect

fun main() {
    class Solution {
        fun isCovered(ranges: Array<IntArray>, left: Int, right: Int): Boolean {
            val remain = (left..right).toMutableSet()

            ranges.forEach {
                remain.removeAll(it[0]..it[1])

                if (remain.isEmpty()) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().isCovered(
            arrayOf(), 1, 2
        )
    }
}
