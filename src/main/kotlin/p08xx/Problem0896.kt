package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isMonotonic(nums: IntArray): Boolean {
            var sign = 0
            for (i in 1 until nums.size) {
                val comp = nums[i].compareTo(nums[i - 1])

                if (comp != 0) {
                    if (comp * sign < 0) {
                        return false
                    }

                    sign = comp
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isMonotonic(
            intArrayOf(1, 2, 2, 3)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}