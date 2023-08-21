package p02xx

import util.expect

fun main() {
    class Solution {
        fun missingNumber(nums: IntArray): Int {
            val target = nums.size.let { it * (it + 1) / 2 }

            return target - nums.sum()
        }
    }

    expect {
        Solution().missingNumber(
            intArrayOf()
        )
    }
}

