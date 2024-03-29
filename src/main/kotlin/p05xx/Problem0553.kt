package p05xx

import util.expect

fun main() {
    class Solution {
        fun optimalDivision(nums: IntArray): String {
            return when (nums.size) {
                1 -> nums[0].toString()
                2 -> "${nums[0]}/${nums[1]}"
                else -> "${nums[0]}/(${nums.drop(1).joinToString("/")})"
            }
        }
    }

    expect {
        Solution().optimalDivision(
            intArrayOf(1, 2, 3, 4)
        )
    }
}