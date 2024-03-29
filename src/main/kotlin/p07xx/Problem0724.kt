package p07xx

import util.expect

fun main() {
    class Solution {
        fun pivotIndex(nums: IntArray): Int {
            val rightSum = IntArray(nums.size)
            var sum = 0

            for (i in nums.lastIndex downTo 0) {
                sum += nums[i]
                rightSum[i] += sum
            }

            sum = 0

            for (i in nums.indices) {
                if (sum == (rightSum.getOrNull(i + 1) ?: 0)) {
                    return i
                }

                sum += nums[i]
            }

            return -1
        }
    }

    expect {
        Solution().pivotIndex(
            intArrayOf(1, 7, 3, 6, 5, 6)
        )
    }
}