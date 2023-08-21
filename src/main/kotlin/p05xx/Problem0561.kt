package p05xx

import util.expect

fun main() {
    class Solution {
        fun arrayPairSum(nums: IntArray): Int {
            nums.sort()

            var result = 0
            for (i in nums.indices step 2) {
                result += nums[i]
            }

            return result
        }
    }

    expect {
        Solution().arrayPairSum(
            intArrayOf(1, 4, 3, 2)
        )
    }
}