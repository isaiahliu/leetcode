package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumSubseqWidths(nums: IntArray): Int {
            val m = 1000000007

            nums.sort()

            var result = 0L
            var base = 2L

            for (i in 1 until nums.size) {
                result += (nums[i] - nums[nums.lastIndex - i]) * (base - 1)
                result %= m

                base = base * 2 % m
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