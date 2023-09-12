package p24xx

import util.expect

fun main() {
    class Solution {
        fun minCost(nums: IntArray, cost: IntArray): Long {
            val indices = nums.indices.sortedBy { nums[it] }

            val min = nums[indices[0]]

            var leftAmount = 0L
            var rightAmount = indices.fold(0L) { sum, i ->
                sum + (nums[i] - min).toLong() * cost[i]
            }
            var leftSum = 0L
            var rightSum = cost.fold(0L) { a, b -> a + b }

            var result = rightAmount

            for (seq in 0 until indices.lastIndex) {
                cost[indices[seq]].also {
                    leftSum += it
                    rightSum -= it
                }

                (nums[indices[seq + 1]] - nums[indices[seq]]).also {
                    leftAmount += leftSum * it
                    rightAmount -= rightSum * it
                }

                result = result.coerceAtMost(leftAmount + rightAmount)
            }

            return result
        }
    }

    expect {
        Solution().minCost(
            intArrayOf(1, 3, 5, 2), intArrayOf(2, 3, 1, 14)
        )
    }
}