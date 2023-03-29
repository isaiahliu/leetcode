package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arrayNesting(nums: IntArray): Int {
            var result = 0

            for (i in nums.indices) {
                var length = -1
                var nextIndex = i

                while (nextIndex >= 0) {
                    length++
                    nextIndex = nums[nextIndex].also {
                        nums[nextIndex] = -1
                    }
                }

                result = result.coerceAtLeast(length)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().arrayNesting(
            intArrayOf(1, 0)
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}