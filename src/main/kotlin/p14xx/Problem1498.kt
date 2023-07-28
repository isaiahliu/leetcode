package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSubseq(nums: IntArray, target: Int): Int {
            nums.sort()
            val TWO = 2.toBigInteger()
            val m = 1000000007
            val mi = 1000000007.toBigInteger()

            var left = 0
            var right = nums.lastIndex

            var result = 0L
            while (left <= right) {
                if (nums[left] + nums[right] > target) {
                    right--
                } else {
                    result += TWO.modPow((right - left).toBigInteger(), mi).toLong()
                    result %= m

                    left++
                }
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().numSubseq(
            intArrayOf(3, 5, 7, 6), 9
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

