package p30xx

import util.expect

fun main() {
    class Solution {
        fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
            val dp = IntArray(nums.size)

            var result = 0

            while (result < nums.size) {
                repeat(nums.size - result) {
                    dp[it] = dp[it] or nums[it + result]

                    if (dp[it] >= k) {
                        return result + 1
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().minimumSubarrayLength(
            intArrayOf(2, 1, 8), 10
        )
    }
}
