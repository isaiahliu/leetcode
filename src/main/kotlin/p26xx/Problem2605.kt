package p26xx

import util.expect

fun main() {
    class Solution {
        fun minNumber(nums1: IntArray, nums2: IntArray): Int {
            return nums1.intersect(nums2.toSet()).minOrNull()
                ?: (nums1.min() * 10 + nums2.min()).coerceAtMost(nums2.min() * 10 + nums1.min())
        }
    }

    expect {
        Solution().minNumber(
            intArrayOf(), intArrayOf()
        )
    }
}