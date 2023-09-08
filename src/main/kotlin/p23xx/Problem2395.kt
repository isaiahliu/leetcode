package p23xx

import util.expect

fun main() {
    class Solution {
        fun findSubarrays(nums: IntArray): Boolean {
            val set = hashSetOf<Int>()

            for (i in 1 until nums.size) {
                if (!set.add(nums[i] + nums[i - 1])) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().findSubarrays(
            intArrayOf()
        )
    }
}