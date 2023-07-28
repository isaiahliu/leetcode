package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun runningSum(nums: IntArray): IntArray {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }
            return nums
        }
    }

    measureTimeMillis {
        Solution().runningSum(
            intArrayOf(1, 4)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

