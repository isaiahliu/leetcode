package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumSubseqWidths(nums: IntArray): Int {
            val m = 1000000007

            nums.sort()

            var result = 0L
            var x = nums[0].toLong()
            var y = 2L
            for (j in 1 until nums.size) {
                result = (result + nums[j] * (y - 1) - x) % m
                x = (x * 2 + nums[j]) % m
                y = y * 2 % m
            }
            return ((result + m) % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().sumSubseqWidths(
            intArrayOf(2, 3, 3, 7)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}