package p24xx

import util.expect

fun main() {
    class Solution {
        fun longestNiceSubarray(nums: IntArray): Int {
            var result = 1

            var left = 0
            var right = 1

            var sum = nums[0]

            while (right < nums.size) {
                if (sum and nums[right] == 0) {
                    sum += nums[right++]

                    result = result.coerceAtLeast(right - left)
                } else {
                    sum -= nums[left++]
                }
            }

            return result
        }
    }

    expect {
        Solution().longestNiceSubarray(
            intArrayOf(1, 2, 33)
        )
    }
}