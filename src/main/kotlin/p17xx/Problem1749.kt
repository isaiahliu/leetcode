package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxAbsoluteSum(nums: IntArray): Int {
            var result = 0

            var neg = 0
            var pos = 0

            nums.forEach {
                neg = (neg + it).coerceAtMost(0)
                pos = (pos + it).coerceAtLeast(0)

                result = result.coerceAtLeast(pos).coerceAtLeast(-neg)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxAbsoluteSum(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

