package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minStartValue(nums: IntArray): Int {
            var min = nums[0]

            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]

                min = min.coerceAtMost(nums[i])
            }

            return (1 - min).coerceAtLeast(1)
        }
    }

    measureTimeMillis {
        Solution().minStartValue(
            intArrayOf(-3, 2 - 3, 4, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

