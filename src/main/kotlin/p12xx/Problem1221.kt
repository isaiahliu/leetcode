package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun balancedStringSplit(s: String): Int {
            var sum = 0
            var result = 0

            s.forEach {
                when (it) {
                    'L' -> sum--
                    'R' -> sum++
                }

                if (sum == 0) {
                    result++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().balancedStringSplit(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
