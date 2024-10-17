package p31xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            var result = 0
            for (index in nums.indices) {
                if (nums[index] == 0) {
                    if (index + 2 < nums.size) {
                        nums[index + 1] = 1 - nums[index + 1]
                        nums[index + 2] = 1 - nums[index + 2]
                    } else {
                        return -1
                    }
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf()
        )
    }
}
