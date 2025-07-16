package p32xx

import util.expect

fun main() {
    class Solution {
        fun maximumLength(nums: IntArray): Int {
            val dp = IntArray(4)

            nums.forEach {
                val d = it and 1

                dp[d] = dp[d xor 1] + 1
                dp[0b10 or d]++
            }

            return dp.max()
        }
    }

    expect {
        Solution().maximumLength(
            intArrayOf()
        )
    }
}
