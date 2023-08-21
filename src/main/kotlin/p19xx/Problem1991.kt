package p19xx

import util.expect

fun main() {
    class Solution {
        fun findMiddleIndex(nums: IntArray): Int {
            val rightSum = IntArray(nums.size)

            var sum = 0
            for (index in nums.lastIndex downTo 0) {
                rightSum[index] = sum
                sum += nums[index]
            }

            sum = 0

            for (index in nums.indices) {
                if (sum == rightSum[index]) {
                    return index
                }

                sum += nums[index]
            }

            return -1
        }
    }

    expect {
        Solution().findMiddleIndex(
            intArrayOf()
        )
    }
}