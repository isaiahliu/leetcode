package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxFrequency(nums: IntArray, k: Int): Int {
            nums.sort()

            var result = 1
            var leftIndex = 0
            var rightIndex = 0
            var cost = 0
            while (rightIndex < nums.lastIndex) {
                rightIndex++

                cost += (rightIndex - leftIndex) * (nums[rightIndex] - nums[rightIndex - 1])

                while (cost > k) {
                    cost -= nums[rightIndex] - nums[leftIndex++]
                }

                result = result.coerceAtLeast(rightIndex - leftIndex + 1)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxFrequency(
            intArrayOf(1, 2, 4), 4
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
