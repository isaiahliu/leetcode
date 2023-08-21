package p14xx

import util.expect

fun main() {
    class Solution {
        fun runningSum(nums: IntArray): IntArray {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }
            return nums
        }
    }

    expect {
        Solution().runningSum(
            intArrayOf(1, 4)
        )

    }
}

