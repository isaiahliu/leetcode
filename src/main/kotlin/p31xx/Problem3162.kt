package p31xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPairs(nums1: IntArray, nums2: IntArray, k: Int): Int {
            return nums1.sumOf { num1 ->
                nums2.count {
                    num1 % (it * k) == 0
                }
            }
        }
    }

    expect {
        Solution().numberOfPairs(
            intArrayOf(0, 1, 1, 1), intArrayOf(), 1
        )
    }
}
