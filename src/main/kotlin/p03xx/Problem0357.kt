package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countNumbersWithUniqueDigits(n: Int): Int {
            if (n == 0) {
                return 1
            }

            var t = 9

            var result = 9

            repeat(n - 1) {
                result *= t--
            }

            result += countNumbersWithUniqueDigits(n - 1)

            return result
        }
    }

    measureTimeMillis {
        Solution().countNumbersWithUniqueDigits(
            2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

