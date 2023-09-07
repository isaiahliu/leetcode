package p23xx

import util.expect

fun main() {
    class Solution {
        fun maximumsSplicedArray(nums1: IntArray, nums2: IntArray): Int {
            var max1 = 0
            var max2 = 0

            var delta1 = 0
            var delta2 = 0
            nums1.forEachIndexed { index, num1 ->
                delta1 += nums2[index] - num1

                if (delta1 < 0) {
                    delta1 = 0
                } else {
                    max1 = max1.coerceAtLeast(delta1)
                }

                delta2 += num1 - nums2[index]

                if (delta2 < 0) {
                    delta2 = 0
                } else {
                    max2 = max2.coerceAtLeast(delta2)
                }
            }

            return (nums1.sum() + max1).coerceAtLeast(nums2.sum() + max2)
        }
    }

    expect {
        Solution().maximumsSplicedArray(
            intArrayOf(10, 20, 50, 15, 30, 10), intArrayOf(40, 20, 10, 100, 10, 10)
        )
    }
}