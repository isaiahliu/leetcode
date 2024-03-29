package p19xx

import util.expect

fun main() {
    class Solution {
        fun canBeIncreasing(nums: IntArray): Boolean {
            var decIndex = -1

            for (i in 1 until nums.size) {
                if (nums[i] <= nums[i - 1]) {
                    if (decIndex < 0) {
                        decIndex = i
                    } else {
                        return false
                    }
                }
            }

            return decIndex < 0 || nums[decIndex - 1] < nums.getOrElse(decIndex + 1) { Int.MAX_VALUE } || nums[decIndex] > nums.getOrElse(
                decIndex - 2
            ) { Int.MIN_VALUE }
        }
    }

    expect {
        Solution().canBeIncreasing(
            intArrayOf(1, 3, 4, 8)
        )
    }
}
