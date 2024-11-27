package p32xx

import util.expect

fun main() {
    class Solution {
        fun countOfPairs(nums: IntArray): Int {
            val m = 1000000007

            var dp = IntArray(51) { if (it <= nums[0]) 1 else 0 }

            for (index in 1 until nums.size) {
                val newDp = IntArray(51)

                for (cur in 0..nums[index]) {
                    for (prev in 0..cur) {
                        if (nums[index - 1] - prev >= nums[index] - cur) {
                            newDp[cur] = (newDp[cur] + dp[prev]) % m
                        }
                    }
                }

                dp = newDp
            }

            return dp.fold(0) { sum, c ->
                (sum + c) % m
            }
        }
    }

    expect {
        Solution().countOfPairs(
            intArrayOf()
        )
    }
}
