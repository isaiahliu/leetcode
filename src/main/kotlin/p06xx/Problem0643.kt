package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaxAverage(nums: IntArray, k: Int): Double {
            var sum = nums.take(k).sum()
            var max = sum

            for (i in k until nums.size) {
                sum -= nums[i - k]
                sum += nums[i]

                max = max.coerceAtLeast(sum)
            }

            return max.toDouble() / k
        }
    }

    measureTimeMillis {
        Solution().findMaxAverage(
            intArrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}