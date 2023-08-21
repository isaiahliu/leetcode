package p17xx

import util.expect

fun main() {
    class Solution {
        fun check(nums: IntArray): Boolean {
            var desc = nums[nums.lastIndex] > nums[0]

            for (i in 1 until nums.size) {
                when {
                    nums[i] >= nums[i - 1] -> {

                    }

                    desc -> return false
                    else -> desc = true
                }
            }

            return true
        }
    }

    expect {
        Solution().check(
            intArrayOf()
        )
    }
}
