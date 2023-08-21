package p01xx

import util.expect

fun main() {
    class Solution {
        fun rob(nums: IntArray): Int {
            if (nums.size <= 2) {
                return nums.max()
            }

            nums[1] = nums[1].coerceAtLeast(nums[0])

            for (i in 2 until nums.size) {
                nums[i] = nums[i - 1].coerceAtLeast(nums[i - 2] + nums[i])
            }

            return nums[nums.lastIndex]
        }
    }

    expect {
        Solution().rob(
            intArrayOf(1, 2, 3, 1)
        )
    }
}

