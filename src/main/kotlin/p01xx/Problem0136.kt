package p01xx

import util.expect

fun main() {
    class Solution {
        fun singleNumber(nums: IntArray): Int {
            return nums.reduce { a, b -> a xor b }
        }
    }

    expect {
        Solution().singleNumber(
            intArrayOf(1, 2, 2)
        )
    }
}

