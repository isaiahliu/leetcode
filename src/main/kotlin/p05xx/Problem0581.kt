package p05xx

import util.expect

fun main() {
    class Solution {
        fun findUnsortedSubarray(nums: IntArray): Int {
            var min = nums[nums.lastIndex]
            var max = nums[0]
            var leftIndex = 0
            var rightIndex = -1
            for (i in nums.indices) {
                if (max > nums[i]) {
                    rightIndex = i
                } else {
                    max = nums[i]
                }

                if (min < nums[nums.size - i - 1]) {
                    leftIndex = nums.size - i - 1
                } else {
                    min = nums[nums.size - i - 1]
                }
            }
            return rightIndex - leftIndex + 1
        }
    }

    expect {
        Solution().findUnsortedSubarray(
            intArrayOf(1, 2, 3, 4)
        )

    }
}