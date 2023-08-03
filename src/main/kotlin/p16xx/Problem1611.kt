package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumOneBitOperations(n: Int): Int {
            var t = n

            var result = 0

            while (t > 0) {
                result = result xor t
                t /= 2
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumOneBitOperations(
            6
        ).also { println("${it} should be ${it}") }
    }
}

