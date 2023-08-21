package p19xx

import util.expect

fun main() {
    class Solution {
        fun maxProductDifference(nums: IntArray): Int {
            nums.sort()

            return nums[nums.lastIndex] * nums[nums.lastIndex - 1] - nums[0] * nums[1]
        }
    }

    expect {
        Solution().maxProductDifference(
            intArrayOf()
        )
    }
}