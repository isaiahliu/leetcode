package p25xx

import util.expect

fun main() {
    class Solution {
        fun getCommon(nums1: IntArray, nums2: IntArray): Int {
            var index1 = 0
            var index2 = 0

            while (index1 < nums1.size && index2 < nums2.size) {
                val num1 = nums1[index1]
                val num2 = nums2[index2]

                when {
                    num1 < num2 -> index1++
                    num1 > num2 -> index2++
                    else -> return num1
                }
            }

            return -1
        }
    }

    expect {
        Solution().getCommon(
            intArrayOf(), intArrayOf()
        )
    }
}

