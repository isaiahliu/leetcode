package p32xx

import util.expect

fun main() {
    class Solution {
        fun countOfPairs(nums: IntArray): Int {
            val m = 1000000007L

            var dp = LongArray(nums[0] + 1) { 1 }

            for (index in 1 until nums.size) {
                dp = LongArray(nums[index] + 1) { cur ->
                    (0..cur).filter { nums[index - 1] - it >= nums[index] - cur }.sumOf { dp.getOrElse(it) { 0L } } % m
                }
            }

            return (dp.sum() % m).toInt()
        }
    }

    expect {
        Solution().countOfPairs(
            intArrayOf()
        )
    }
}
