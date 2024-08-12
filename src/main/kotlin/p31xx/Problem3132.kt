package p31xx

import util.expect

fun main() {
    class Solution {
        fun minimumAddedInteger(nums1: IntArray, nums2: IntArray): Int {
            nums1.sort()
            nums2.sort()

            for (ignoreIndex1 in 0 until nums1.lastIndex) {
                nextLoop@ for (ignoreIndex2 in ignoreIndex1 + 1 until nums1.size) {
                    var index2 = 0
                    var diff: Int? = null

                    for (index1 in nums1.indices) {
                        if (index1 != ignoreIndex1 && index1 != ignoreIndex2) {
                            val d = nums2[index2++] - nums1[index1]

                            if (diff == null) {
                                diff = d
                            } else if (diff != d) {
                                continue@nextLoop
                            }
                        }
                    }

                    return diff ?: 0
                }
            }

            return -1
        }
    }

    expect {
        Solution().minimumAddedInteger(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(1, 2, 3, 4)
        )
    }
}
