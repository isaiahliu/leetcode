package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countNumbersWithUniqueDigits(n: Int): Int {
            if (n == 0) {
                return 1
            }

            var t = 9.toBigInteger()

            var result = 9.toBigInteger()

            repeat(n - 1) {
                result *= t
                t--
            }

            result += countNumbersWithUniqueDigits(n - 1).toBigInteger()

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().countNumbersWithUniqueDigits(
            2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

