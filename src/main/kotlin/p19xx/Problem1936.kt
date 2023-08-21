package p19xx

import util.expect

fun main() {
    class Solution {
        fun addRungs(rungs: IntArray, dist: Int): Int {
            var result = 0

            var pre = 0
            rungs.forEach {
                result += (it - pre - 1).coerceAtLeast(0) / dist
                pre = it
            }

            return result
        }
    }

    expect {
        Solution().addRungs(
            intArrayOf(), 1
        )
    }
}