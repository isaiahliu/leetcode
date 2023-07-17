package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findPeakElement(nums: IntArray): Int {
            var leftIndex = 0
            var rightIndex = nums.lastIndex

            while (leftIndex < rightIndex) {
                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                if (nums[midIndex] < nums[midIndex + 1]) {
                    leftIndex = midIndex + 1
                } else {
                    rightIndex = midIndex
                }
            }

            return leftIndex
        }
    }

    measureTimeMillis {
        Solution().findPeakElement(
            intArrayOf(1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

