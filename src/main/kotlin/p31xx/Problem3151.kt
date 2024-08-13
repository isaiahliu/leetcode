package p31xx

import util.expect

fun main() {
    class Solution {
        fun isArraySpecial(nums: IntArray): Boolean {
            var next = nums[0] % 2

            nums.forEach {
                val n = it % 2

                if (n == next) {
                    next = 1 - next
                } else {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isArraySpecial(
            intArrayOf()
        )
    }
}
