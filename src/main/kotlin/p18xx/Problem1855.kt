package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDistance(nums1: IntArray, nums2: IntArray): Int {
            var result = 0
            var right = 0

            for ((index, num) in nums1.withIndex()) {
                right = right.coerceAtLeast(index)
                while (right < nums2.size && nums2[right] >= num) {
                    right++
                }

                result = result.coerceAtLeast(right - index - 1)

                if (right == nums2.size) {
                    break
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxDistance(
            intArrayOf(), intArrayOf()
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
