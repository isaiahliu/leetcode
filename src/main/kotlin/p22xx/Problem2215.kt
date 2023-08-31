package p22xx

import util.expect

fun main() {
    class Solution {
        fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
            val set1 = nums1.toSet()
            val set2 = nums2.toSet()

            return listOf((set1 - set2).toList(), (set2 - set1).toList())
        }
    }

    expect {
        Solution().findDifference(
            intArrayOf(), intArrayOf()
        )
    }
}