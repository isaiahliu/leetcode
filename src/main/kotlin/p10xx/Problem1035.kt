package p10xx

import util.expect

fun main() {
    class Solution {
        fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
            val dp = Array(nums1.size) {
                IntArray(nums2.size)
            }

            for (i1 in nums1.indices) {
                for (i2 in nums2.indices) {
                    val up = dp.getOrNull(i1 - 1)?.getOrNull(i2) ?: 0
                    val left = dp.getOrNull(i1)?.getOrNull(i2 - 1) ?: 0

                    var lu = dp.getOrNull(i1 - 1)?.getOrNull(i2 - 1) ?: 0
                    if (nums1[i1] == nums2[i2]) {
                        lu++
                    }

                    dp[i1][i2] = up.coerceAtLeast(left).coerceAtLeast(lu)
                }
            }

            return dp[nums1.lastIndex][nums2.lastIndex]
        }
    }

    expect {
        Solution().maxUncrossedLines(
            intArrayOf(), intArrayOf()
        )
    }
}
