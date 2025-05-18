package p30xx

import util.expect

fun main() {
    class Solution {
        fun triangleType(nums: IntArray): String {
            nums.sort()

            return when {
                nums[0] + nums[1] <= nums[2] -> "none"
                nums[0] == nums[2] -> "equilateral"
                nums[0] == nums[1] || nums[1] == nums[2] -> "isosceles"
                else -> "scalene"
            }
        }
    }

    expect {
        Solution().triangleType(
            intArrayOf()
        )
    }
}
