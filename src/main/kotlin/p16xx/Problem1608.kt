package p16xx

import util.expect

fun main() {
    class Solution {
        fun specialArray(nums: IntArray): Int {
            nums.sortDescending()

            if (nums[nums.lastIndex] >= nums.size) {
                return nums.size
            }

            for (i in 1 until nums.size) {
                if (nums[i - 1] >= i && i > nums[i]) {
                    return i
                }
            }

            return -1
        }
    }

    expect {
        Solution().specialArray(
            intArrayOf()
        )
    }
}

