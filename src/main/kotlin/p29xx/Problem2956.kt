package p29xx

import util.expect

fun main() {
    class Solution {
        fun findIntersectionValues(nums1: IntArray, nums2: IntArray): IntArray {
            return intArrayOf(
                nums1.count { it in nums2 },
                nums2.count { it in nums1 }
            )
        }
    }
    expect {
        Solution().findIntersectionValues(
            intArrayOf(), intArrayOf()
        )
    }
}
