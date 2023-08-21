package p18xx

import util.expect

fun main() {
    class Solution {
        fun subsetXORSum(nums: IntArray): Int {
            return nums.fold(0) { a, b -> a or b } shl (nums.size - 1)
        }
    }

    expect {
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2)
        )

        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3)
        )

        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14, 15)
        )
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14, 15, 15)
        )
        Solution().subsetXORSum(
            IntArray(16) { 2 }
        )

    }
}
