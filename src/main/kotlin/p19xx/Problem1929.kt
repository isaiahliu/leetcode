package p19xx

import util.expect

fun main() {
    class Solution {
        fun getConcatenation(nums: IntArray): IntArray {
            return nums + nums
        }
    }

    expect {
        Solution().getConcatenation(
            intArrayOf(1, 2, 1)
        )
    }
}