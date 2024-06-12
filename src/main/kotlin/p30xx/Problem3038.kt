package p30xx

import util.expect

fun main() {
    class Solution {
        fun maxOperations(nums: IntArray): Int {
            val target = nums[0] + nums[1]

            var index = 1
            while (index < nums.size) {
                if (nums[index - 1] + nums[index] == target) {
                    index += 2
                } else {
                    break
                }
            }

            return index / 2
        }
    }

    expect {
        Solution().maxOperations(
            intArrayOf()
        )
    }
}
