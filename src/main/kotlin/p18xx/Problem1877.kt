package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minPairSum(nums: IntArray): Int {
            nums.sort()

            var result = 0
            repeat(nums.size / 2) {
                result = result.coerceAtLeast(nums[it] + nums[nums.lastIndex - it])
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minPairSum(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
