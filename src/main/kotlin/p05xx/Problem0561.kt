package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arrayPairSum(nums: IntArray): Int {
            nums.sort()

            var result = 0
            for (i in nums.indices step 2) {
                result += nums[i]
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().arrayPairSum(
            intArrayOf(1, 4, 3, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}