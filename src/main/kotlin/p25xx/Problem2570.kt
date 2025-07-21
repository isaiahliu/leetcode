package p25xx

import util.expect

fun main() {
    class Solution {
        fun mergeArrays(nums1: Array<IntArray>, nums2: Array<IntArray>): Array<IntArray> {
            var leftIndex = 0
            var rightIndex = 0

            val result = arrayListOf<IntArray>()

            while (leftIndex < nums1.size || rightIndex < nums2.size) {
                when {
                    leftIndex == nums1.size || rightIndex < nums2.size && nums1[leftIndex][0] > nums2[rightIndex][0] -> {
                        result.add(nums2[rightIndex++])
                    }

                    rightIndex == nums2.size || nums1[leftIndex][0] < nums2[rightIndex][0] -> {
                        result.add(nums1[leftIndex++])
                    }

                    else -> {
                        result += intArrayOf(nums1[leftIndex][0], nums1[leftIndex++][1] + nums2[rightIndex++][1])
                    }
                }
            }

            return result.toTypedArray()
        }
    }

    expect {
        Solution().mergeArrays(
            arrayOf(
                intArrayOf(2, 4),
                intArrayOf(3, 6),
                intArrayOf(5, 5),
            ), arrayOf(
                intArrayOf(1, 5),
                intArrayOf(4, 5),
            )
        )
    }
}
