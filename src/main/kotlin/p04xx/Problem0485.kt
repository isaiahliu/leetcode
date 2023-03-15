package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaxConsecutiveOnes(nums: IntArray): Int {
            var result = nums[0]

            for (i in 1 until nums.size) {
                if (nums[i] > 0) {
                    nums[i] += nums[i - 1]

                    result = result.coerceAtLeast(nums[i])
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findMaxConsecutiveOnes(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}