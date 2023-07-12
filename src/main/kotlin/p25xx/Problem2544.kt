package p25xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun alternateDigitSum(n: Int): Int {
            var t = n
            var result = 0

            while (t > 0) {
                result *= -1
                result += t % 10

                t /= 10
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().alternateDigitSum(
            12345
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
