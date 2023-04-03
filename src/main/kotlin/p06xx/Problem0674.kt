package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLengthOfLCIS(nums: IntArray): Int {
            var pre = nums[0]
            var size = 1
            var maxSize = 1

            nums.forEach {
                if (it > pre) {
                    size++

                    maxSize = maxSize.coerceAtLeast(size)
                } else {
                    size = 1
                }

                pre = it
            }

            return maxSize
        }
    }

    measureTimeMillis {
        Solution().findLengthOfLCIS(
            intArrayOf(
                1, 3, 5, 4, 7
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}