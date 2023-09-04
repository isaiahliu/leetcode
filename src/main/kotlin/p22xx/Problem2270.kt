package p22xx

import util.expect

fun main() {
    class Solution {
        fun waysToSplitArray(nums: IntArray): Int {
            val sum = nums.fold(0L) { a, b -> a + b }

            var result = 0

            var s = 0L
            for (i in 0 until nums.lastIndex) {
                s += nums[i]

                if (s + s >= sum) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().waysToSplitArray(
            intArrayOf()
        )
    }
}