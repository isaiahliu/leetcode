package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun smallestRangeII(nums: IntArray, k: Int): Int {
            nums.sort()

            var result: Int = nums[nums.lastIndex] - nums[0]

            for (i in 0 until nums.size - 1) {
                val high = (nums[nums.lastIndex] - k).coerceAtLeast(nums[i] + k)
                val low = (nums[0] + k).coerceAtMost(nums[i + 1] - k)
                result = result.coerceAtMost(high - low)
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().smallestRangeII(
            intArrayOf(1, 3, 6), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}