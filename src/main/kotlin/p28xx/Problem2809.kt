package p28xx

import util.expect

fun main() {
    class Solution {
        fun minimumTime(nums1: List<Int>, nums2: List<Int>, x: Int): Int {
            val s1 = nums1.sum()
            val s2 = nums2.sum()
            val dp = Array(nums1.size + 1) {
                IntArray(nums1.size + 1)
            }

            val indices = nums1.indices.sortedBy { nums2[it] }

            for (j in nums1.indices) {
                for (i in j downTo 0) {
                    dp[j + 1][i + 1] = maxOf(dp[j][i + 1], (dp[j][i] + (i + 1) * nums2[indices[j]] + nums1[indices[j]]))
                }
            }
            for (i in 0..nums1.size) {
                if (s2 * i + s1 - dp[nums1.size][i] <= x) {
                    return i
                }
            }
            return -1
        }
    }

    expect(4) {
        Solution().minimumTime(
            listOf(1, 7, 6, 2, 9), listOf(4, 2, 3, 3, 0), 23
        )
    }


    expect(6) {
        Solution().minimumTime(
            listOf(6, 5, 2, 8, 8, 1, 6, 4), listOf(1, 2, 1, 0, 1, 0, 3, 1), 23
        )
    }

    expect {
        Solution().minimumTime(
            listOf(1, 2, 3), listOf(1, 2, 3), 4
        )
    }
}
