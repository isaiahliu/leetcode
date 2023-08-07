package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfMatches(n: Int): Int {
            if (n == 1) {
                return 0
            }

            return n / 2 + numberOfMatches(n / 2 + n % 2)
        }
    }

    measureTimeMillis {
        Solution().numberOfMatches(
            14
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

