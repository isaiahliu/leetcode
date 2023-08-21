package p14xx

import util.expect

fun main() {
    class Solution {
        fun minStartValue(nums: IntArray): Int {
            var min = nums[0]

            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]

                min = min.coerceAtMost(nums[i])
            }

            return (1 - min).coerceAtLeast(1)
        }
    }

    expect {
        Solution().minStartValue(
            intArrayOf(-3, 2 - 3, 4, 2)
        )
    }
}

