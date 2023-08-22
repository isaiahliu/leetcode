package p20xx

import util.expect

fun main() {
    class Solution {
        fun maximumDifference(nums: IntArray): Int {
            var result = 0

            var min = nums[0]

            for (i in 1 until nums.size) {
                result = result.coerceAtLeast(nums[i] - min)

                min = min.coerceAtMost(nums[i])
            }

            return result.takeIf { it > 0 } ?: -1
        }
    }

    expect {
        Solution().maximumDifference(
            intArrayOf()
        )
    }
}