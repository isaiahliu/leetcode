package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkPerfectNumber(num: Int): Boolean {
            if (num == 1) {
                return false
            }

            val factors = hashSetOf(1)

            var factor = 2

            while (factor * factor <= num) {
                if (num % factor == 0) {
                    factors.add(factor)
                    factors.add(num / factor)
                }

                factor++
            }

            return factors.sum() == num
        }
    }

    measureTimeMillis {
        Solution().checkPerfectNumber(
            28
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}