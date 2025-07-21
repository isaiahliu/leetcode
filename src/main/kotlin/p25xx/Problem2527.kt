package p25xx

import util.expect

fun main() {
    class Solution {
        fun xorBeauty(nums: IntArray): Int {
            return nums.reduce { a, b -> a xor b }
        }
    }

    expect {
        Solution().xorBeauty(
            intArrayOf()
        )
    }
}