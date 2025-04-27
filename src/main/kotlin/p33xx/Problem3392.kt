package p33xx

import util.expect

fun main() {
    class Solution {
        fun countSubarrays(nums: IntArray): Int {
            return (1 until nums.lastIndex).count {
                nums[it] == (nums[it - 1] + nums[it + 1]) * 2
            }
        }
    }

    expect {
        Solution().countSubarrays(
            intArrayOf()
        )
    }
}
