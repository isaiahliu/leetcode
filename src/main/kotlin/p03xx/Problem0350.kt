package p03xx

import util.expect

fun main() {
    class Solution {
        fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
            val num1List = nums1.toMutableList()

            val result = arrayListOf<Int>()
            nums2.forEach {
                if (num1List.remove(it)) {
                    result.add(it)
                }
            }

            return result.toIntArray()
        }
    }
    expect {
        Solution().intersect(
            intArrayOf(), intArrayOf()
        )
    }
}

