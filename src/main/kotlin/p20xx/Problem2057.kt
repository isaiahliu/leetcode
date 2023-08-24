package p20xx

import util.expect

fun main() {
    class Solution {
        fun smallestEqual(nums: IntArray): Int {
            return nums.indices.indexOfFirst { it % 10 == nums[it] }
        }
    }

    expect {
        Solution().smallestEqual(
            intArrayOf(0, 1, 2)
        )
    }
}