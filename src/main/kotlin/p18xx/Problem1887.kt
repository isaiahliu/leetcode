package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reductionOperations(nums: IntArray): Int {
            nums.sort()

            var result = 0
            var incCount = 0
            var pre = nums[0]
            for (num in nums) {
                if (num > pre) {
                    incCount++
                }

                pre = num
                result += incCount
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().reductionOperations(
            intArrayOf(1, 1, 2, 2, 3)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
