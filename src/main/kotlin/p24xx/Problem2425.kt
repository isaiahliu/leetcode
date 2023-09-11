package p24xx

import util.expect

fun main() {
    class Solution {
        fun xorAllNums(nums1: IntArray, nums2: IntArray): Int {
            val sum1 = nums1.fold(0) { a, b -> a xor b }
            val sum2 = nums2.fold(0) { a, b -> a xor b }
            return sum1 * (nums2.size % 2) xor sum2 * (nums1.size % 2)
        }
    }

    expect {
        Solution().xorAllNums(
            intArrayOf(2, 1, 3), intArrayOf(10, 2, 5, 0)
        )
    }
}