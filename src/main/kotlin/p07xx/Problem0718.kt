package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLength(nums1: IntArray, nums2: IntArray): Int {
            val dp = Array(nums1.size) {
                IntArray(nums2.size)
            }

            var max = 0
            nums1.forEachIndexed { idx1, n1 ->
                nums2.forEachIndexed { idx2, n2 ->
                    dp[idx1][idx2] = if (n1 == n2) {
                        (dp.getOrNull(idx1 - 1)?.getOrNull(idx2 - 1) ?: 0) + 1
                    } else {
                        0
                    }

                    max = max.coerceAtLeast(dp[idx1][idx2])
                }
            }

            return max
        }
    }

    measureTimeMillis {
        Solution().findLength(
            intArrayOf(1, 2, 3, 2, 1), intArrayOf(3, 2, 1, 4, 7)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}