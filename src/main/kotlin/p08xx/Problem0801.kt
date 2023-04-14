package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSwap(nums1: IntArray, nums2: IntArray): Int {
            val MAX = 9999999
            val dp = Array(nums1.size) { IntArray(2) }

            dp[0][1] = 1

            for (i in 1 until nums1.size) {
                val gtSame = nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]
                val gtDiff = nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]

                dp[i][0] = arrayOf(if (gtSame) dp[i - 1][0] else MAX, if (gtDiff) dp[i - 1][1] else MAX).min()
                dp[i][1] = arrayOf(if (gtSame) dp[i - 1][1] + 1 else MAX, if (gtDiff) dp[i - 1][0] + 1 else MAX).min()
            }

            return dp[dp.lastIndex].let { it[0].coerceAtMost(it[1]) }
        }
    }

    measureTimeMillis {
        Solution().minSwap(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}