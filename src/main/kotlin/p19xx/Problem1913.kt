package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProductDifference(nums: IntArray): Int {
            nums.sort()

            return nums[nums.lastIndex] * nums[nums.lastIndex - 1] - nums[0] * nums[1]
        }
    }

    measureTimeMillis {
        Solution().maxProductDifference(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}