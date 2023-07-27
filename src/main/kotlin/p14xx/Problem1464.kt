package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProduct(nums: IntArray): Int {
            nums.sort()

            return ((nums[0] - 1) * (nums[1] - 1)).coerceAtLeast((nums[nums.lastIndex] - 1) * (nums[nums.lastIndex - 1] - 1))
        }
    }

    measureTimeMillis {
        Solution().maxProduct(
            intArrayOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

