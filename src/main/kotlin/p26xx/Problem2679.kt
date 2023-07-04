package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun matrixSum(nums: Array<IntArray>): Int {
            nums.forEach {
                it.sort()
            }

            var result = 0
            for (index in nums[0].indices) {
                var max = Int.MIN_VALUE

                nums.forEach {
                    max = max.coerceAtLeast(it[index])
                }

                result += max
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().matrixSum(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
