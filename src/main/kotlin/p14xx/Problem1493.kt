package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestSubarray(nums: IntArray): Int {
            var count = 0
            val leftOnes = IntArray(nums.size) {
                count.apply {
                    if (nums[it] == 1) {
                        count++
                    } else {
                        count = 0
                    }
                }
            }

            count = 0
            var result = 0
            for (index in nums.lastIndex downTo 0) {
                result = result.coerceAtLeast(leftOnes[index] + count)

                if (nums[index] == 1) {
                    count++
                } else {
                    count = 0
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestSubarray(
            intArrayOf(0, 1, 1, 1, 0, 1, 1, 0, 1)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

