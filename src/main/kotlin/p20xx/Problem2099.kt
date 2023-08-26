package p20xx

import util.expect

fun main() {
    class Solution {
        fun maxSubsequence(nums: IntArray, k: Int): IntArray {
            return nums.indices.sortedByDescending { nums[it] }.take(k).sorted().map { nums[it] }.toIntArray()
        }
    }

    expect {
        Solution().maxSubsequence(
            intArrayOf(), 1
        )
    }
}