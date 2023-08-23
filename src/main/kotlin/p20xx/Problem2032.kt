package p20xx

import util.expect

fun main() {
    class Solution {
        fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
            val map = hashMapOf<Int, Int>()

            arrayOf(nums1, nums2, nums3).forEach {
                it.toSet().forEach {
                    map[it] = (map[it] ?: 0) + 1
                }
            }

            return map.filterValues { it > 1 }.keys.toList()
        }
    }

    expect {
        Solution().twoOutOfThree(
            intArrayOf(), intArrayOf(), intArrayOf()
        )
    }
}