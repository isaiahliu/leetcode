package p19xx

import util.expect

fun main() {
    class Solution {
        fun minimumDifference(nums: IntArray, k: Int): Int {
            if (k == 1) {
                return 0
            }
            nums.sort()

            var result = Int.MAX_VALUE

            for (index in 0 until nums.size - k + 1) {
                result = result.coerceAtMost(nums[index + k - 1] - nums[index])
            }

            return result
        }
    }

    expect {
        Solution().minimumDifference(
            intArrayOf(), 1
        )
    }
}