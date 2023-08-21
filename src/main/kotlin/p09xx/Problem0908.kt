package p09xx

import util.expect

fun main() {
    class Solution {
        fun smallestRangeI(nums: IntArray, k: Int): Int {
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE

            nums.forEach {
                min = min.coerceAtMost(it)
                max = max.coerceAtLeast(it)
            }

            return (max - min - k * 2).coerceAtLeast(0)
        }
    }

    expect {
        Solution().smallestRangeI(
            intArrayOf(71, 55, 82, 55), 1
        )
    }
}