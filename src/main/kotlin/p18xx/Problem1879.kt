package p18xx

import util.expect

fun main() {
    class Solution {
        fun minimumXORSum(nums1: IntArray, nums2: IntArray): Int {
            val dp = IntArray(1 shl nums1.size) { Int.MAX_VALUE }

            dp[0] = 0

            for (d in 1 until dp.size) {
                nums2.forEachIndexed { num2Index, n ->
                    (1 shl num2Index).takeIf { it and d > 0 }?.also { num2Pos ->
                        dp[d] = dp[d].coerceAtMost(dp[d xor num2Pos] + (nums1[Integer.bitCount(d) - 1] xor n))
                    }
                }
            }

            return dp.last()
        }
    }

    expect {
        Solution().minimumXORSum(
            intArrayOf(72, 97, 8, 32, 15),
            intArrayOf(63, 97, 57, 60, 83),
        )
    }
}

