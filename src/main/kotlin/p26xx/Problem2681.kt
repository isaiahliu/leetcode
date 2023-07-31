package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumOfPower(nums: IntArray): Int {
            nums.sort()

            val m = 1000000007

            var result = 0L

            var minSum = 0L
            nums.forEach { num ->
                result += ((num.toLong() * num) % m) * (minSum + num)
                result %= m

                minSum = (minSum * 2 + num) % m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().sumOfPower(
            intArrayOf(1, 2, 4),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
