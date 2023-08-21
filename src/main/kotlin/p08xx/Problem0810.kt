package p08xx

import util.expect

fun main() {
    class Solution {
        fun xorGame(nums: IntArray): Boolean {
            return nums.size % 2 == 0 || nums.fold(0) { a, b -> a xor b } == 0
        }
    }

    expect {
        Solution().xorGame(
            intArrayOf(1, 1, 2)
        )
    }
}