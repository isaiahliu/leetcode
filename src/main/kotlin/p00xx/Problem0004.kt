package p00xx

import util.expect

fun main() {
    class Solution {
        fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            val totalCount = nums1.size + nums2.size

            var index1 = 0
            var index2 = 0

            fun readNext(): Int {
                val left = nums1.getOrNull(index1)
                val right = nums2.getOrNull(index2)

                val leftMin = when {
                    left == null -> false
                    right == null -> true
                    left < right -> true
                    else -> false
                }

                return if (leftMin) {
                    nums1[index1].also { index1++ }
                } else {
                    nums2[index2].also { index2++ }
                }
            }

            repeat((totalCount + 1) / 2 - 1) {
                readNext()
            }

            return if (totalCount % 2 == 0) {
                (readNext() + readNext()) / 2.0
            } else {
                readNext().toDouble()
            }
        }
    }

    expect {
        Solution().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))
    }
}

