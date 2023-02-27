package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun movesToMakeZigzag(nums: IntArray): Int {
            var resultEven = 0
            var resultOdd = 0

            for (i in nums.indices) {
                val left = nums.getOrNull(i - 1) ?: 9999
                val right = nums.getOrNull(i + 1) ?: 9999

                val movement = (nums[i] - (left.coerceAtMost(right) - 1)).coerceAtLeast(0)

                if (i % 2 == 0) {
                    resultEven += movement
                } else {
                    resultOdd += movement
                }
            }

            return resultOdd.coerceAtMost(resultEven)
        }
    }

    measureTimeMillis {
        Solution().movesToMakeZigzag(intArrayOf()).also { println(it) }
    }
}

