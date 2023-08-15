package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            var min = nums[0]
            var result = 0
            nums.forEach {
                if (min > it) {
                    result += min - it
                }

                min = min.coerceAtLeast(it) + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minOperations(
            intArrayOf(1, 1, 1)
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
