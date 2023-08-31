package p22xx

import util.expect

fun main() {
    class Solution {
        fun countHillValley(nums: IntArray): Int {
            val left = IntArray(nums.size) { nums[it] }
            val right = IntArray(nums.size) { nums[it] }

            var pre = nums[0]
            for (i in 1 until nums.size) {
                if (nums[i] != nums[i - 1]) {
                    pre = nums[i - 1]
                }

                left[i] = pre
            }

            pre = nums.last()
            for (i in nums.lastIndex - 1 downTo 0) {
                if (nums[i] != nums[i + 1]) {
                    pre = nums[i + 1]
                }

                right[i] = pre
            }

            return (1 until nums.size).count {
                nums[it] != nums[it - 1] && (nums[it] > left[it] && nums[it] > right[it] || nums[it] < left[it] && nums[it] < right[it])
            }
        }
    }

    expect {
        Solution().countHillValley(
            intArrayOf(2, 4, 1, 1, 6, 5)
        )
    }
}