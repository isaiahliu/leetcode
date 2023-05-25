package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun prefixesDivBy5(nums: IntArray): List<Boolean> {
            var sum = 0

            return List(nums.size) {
                sum *= 2
                sum += nums[it]
                sum %= 5
                sum == 0
            }
        }
    }

    measureTimeMillis {
        Solution().prefixesDivBy5(
            intArrayOf(1, 1, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}