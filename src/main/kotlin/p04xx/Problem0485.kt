package p04xx

import util.expect

fun main() {
    class Solution {
        fun findMaxConsecutiveOnes(nums: IntArray): Int {
            var result = nums[0]

            for (i in 1 until nums.size) {
                if (nums[i] > 0) {
                    nums[i] += nums[i - 1]

                    result = result.coerceAtLeast(nums[i])
                }
            }

            return result
        }
    }

    expect {
        Solution().findMaxConsecutiveOnes(
            intArrayOf()
        )
    }
}