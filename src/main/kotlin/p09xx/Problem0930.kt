package p09xx

import util.expect

fun main() {
    class Solution {
        fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
            val sumMap = hashMapOf(0 to 1)

            var result = 0
            var sum = 0
            nums.forEach { num ->
                sum += num

                sumMap[sum - goal]?.also { result += it }

                sumMap[sum] = (sumMap[sum] ?: 0) + 1
            }
            return result
        }
    }

    expect {
        Solution().numSubarraysWithSum(
            intArrayOf(1, 0, 1), 1
        )
    }
}