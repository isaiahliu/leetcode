package p17xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums1: IntArray, nums2: IntArray): Int {
            val (lessSize, moreSize) = if (nums1.size < nums2.size) arrayOf(nums1.size, nums2.size) else arrayOf(
                nums2.size, nums1.size
            )

            if (lessSize * 6 < moreSize) {
                return -1
            }

            val (less, more) = if (nums1.sum() < nums2.sum()) arrayOf(nums1, nums2) else arrayOf(nums2, nums1)
            val lessSum = less.sum()
            val moreSum = more.sum()

            less.sort()
            more.sortDescending()

            var delta = moreSum - lessSum
            var left = 0
            var right = 0
            var result = 0

            while (delta > 0) {
                val lessAdj = less.getOrNull(left)?.let { 6 - it } ?: 0
                val rightAdj = more.getOrNull(right)?.let { it - 1 } ?: 0

                delta -= if (lessAdj > rightAdj) {
                    6 - less[left++]
                } else {
                    more[right++] - 1
                }

                result++
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(), intArrayOf()
        )
    }
}
