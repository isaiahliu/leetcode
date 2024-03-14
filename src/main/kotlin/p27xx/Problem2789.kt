package p27xx

import util.expect

fun main() {
    class Solution {
        fun maxArrayValue(nums: IntArray): Long {
            var result = nums.last().toLong()

            for (i in nums.lastIndex - 1 downTo 0) {
                if (nums[i] > result) {
                    result = 0
                }

                result += nums[i]
            }

            return result
        }
    }

    expect {
        Solution().maxArrayValue(
            intArrayOf()
        )
    }
}
