package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestOnes(nums: IntArray, k: Int): Int {
            var result = 0

            var l = 0
            var r = 0

            var zeros = 0

            while (r < nums.size) {
                zeros += (1 - nums[r++])

                while (zeros > k) {
                    zeros -= (1 - nums[l++])
                }

                result = result.coerceAtLeast(r - l)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestOnes(
            intArrayOf(1, 0, 1, 1), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
