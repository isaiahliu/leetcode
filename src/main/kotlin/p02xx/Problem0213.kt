package p02xx

import util.expect

fun main() {
    class Solution {
        fun rob(nums: IntArray): Int {
            if (nums.size <= 2) {
                return nums.max()
            }

            //steal 0
            val dp1 = IntArray(nums.size)
            dp1[0] = nums[0]
            dp1[1] = nums[0]
            for (i in 2 until nums.lastIndex) {
                dp1[i] = dp1[i - 1].coerceAtLeast(dp1[i - 2] + nums[i])
            }

            //not steal 0
            val dp2 = IntArray(nums.size)
            dp2[1] = nums[1]

            for (i in 2 until nums.size) {
                dp2[i] = dp2[i - 1].coerceAtLeast(dp2[i - 2] + nums[i])
            }

            return dp1[nums.size - 2].coerceAtLeast(dp2[nums.lastIndex])
        }
    }

    expect {
        Solution().rob(
            intArrayOf(4, 1, 2, 7, 5, 3, 1)
        )
    }
}

