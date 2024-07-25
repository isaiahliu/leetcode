package p27xx

import util.expect

fun main() {
    class Solution {
        fun findValueOfPartition(nums: IntArray): Int {
            nums.sort()

            return (0 until nums.lastIndex).minOf { nums[it + 1] - nums[it] }
        }
    }

    expect(119) {
        Solution().findValueOfPartition(
            intArrayOf()
        )
    }
}
