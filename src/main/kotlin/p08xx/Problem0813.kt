package p08xx

import util.expect

fun main() {
    class Solution {
        fun largestSumOfAverages(nums: IntArray, k: Int): Double {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            val dp = Array(nums.size) {
                DoubleArray(k)
            }

            nums.indices.forEach { index ->
                dp[index][0] = nums[index].toDouble() / (index + 1)

                for (group in 1 until k.coerceAtMost(index + 1)) {
                    for (i in group..index) {
                        dp[index][group] =
                            dp[index][group].coerceAtLeast(dp[i - 1][group - 1] + (nums[index] - nums[i - 1]).toDouble() / (index - i + 1))
                    }
                }
            }

            return dp[nums.lastIndex][k - 1]
        }
    }

    expect {
        Solution().largestSumOfAverages(
            intArrayOf(4, 1, 7, 5, 6, 2, 3), 4
        )
    }
}