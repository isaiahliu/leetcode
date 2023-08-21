package p18xx

import util.expect

fun main() {
    class Solution {
        fun minPairSum(nums: IntArray): Int {
            nums.sort()

            var result = 0
            repeat(nums.size / 2) {
                result = result.coerceAtLeast(nums[it] + nums[nums.lastIndex - it])
            }

            return result
        }
    }

    expect {
        Solution().minPairSum(
            intArrayOf()
        )
    }
}
