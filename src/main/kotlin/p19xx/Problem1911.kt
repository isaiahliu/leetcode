package p19xx

import util.expect

fun main() {
    class Solution {
        fun maxAlternatingSum(nums: IntArray): Long {
            val dp = Array(nums.size) {
                longArrayOf(0, 0)
            }

            dp[0][0] = nums[0].toLong()

            for (i in 1 until nums.size) {
                dp[i][0] = dp[i - 1][0].coerceAtLeast(dp[i - 1][1] + nums[i])
                dp[i][1] = dp[i - 1][1].coerceAtLeast(dp[i - 1][0] - nums[i])
            }

            return dp[dp.lastIndex].let { it[0].coerceAtLeast(it[1]) }
        }
    }

    expect {
        Solution().maxAlternatingSum(
            intArrayOf(1, 2, 3, 4, 5)
        )
    }
}

