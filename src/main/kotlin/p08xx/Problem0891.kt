package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumSubseqWidths(nums: IntArray): Int {
            val m = 1000000007

            nums.sort()

            var result = 0L
            var base = 1L

            for (i in 1 until nums.size) {
                result += (nums[i] - nums[nums.lastIndex - i]) * base
                result %= m

                base = ((base + 1) * 2 - 1) % m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().sumSubseqWidths(
            intArrayOf(2, 3, 3, 7)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}