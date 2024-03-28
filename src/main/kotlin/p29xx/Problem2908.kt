package p29xx

import util.expect

fun main() {
    class Solution {
        fun minimumSum(nums: IntArray): Int {
            val leftMin = IntArray(nums.size)
            val rightMin = IntArray(nums.size)

            var min = nums[0]
            for (index in 1 until nums.size) {
                leftMin[index] = min
                min = minOf(min, nums[index])
            }

            min = nums.last()
            for (index in nums.lastIndex - 1 downTo 0) {
                rightMin[index] = min
                min = minOf(min, nums[index])
            }

            return (1 until nums.lastIndex).filter {
                nums[it] > leftMin[it] && nums[it] > rightMin[it]
            }.minOfOrNull { nums[it] + leftMin[it] + rightMin[it] } ?: -1
        }
    }

    expect {
        Solution().minimumSum(
            intArrayOf(7, 10, 2)
        )
    }
}
