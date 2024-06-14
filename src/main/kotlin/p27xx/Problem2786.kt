package p27xx

import util.expect

fun main() {
    class Solution {
        fun maxScore(nums: IntArray, x: Int): Long {
            val dp = LongArray(2) { -99999999 }
            dp[nums[0] % 2] = nums[0].toLong()

            for (i in 1 until nums.size) {
                val n = nums[i]

                val max = maxOf(dp[n % 2], dp[1 - n % 2] - x) + n

                dp[n % 2] = maxOf(dp[n % 2], max)
            }
            return dp.max()
        }
    }

    expect {
        Solution().maxScore(
            intArrayOf(), 1
        )
    }
}
