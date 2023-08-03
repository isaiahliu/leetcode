package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumOneBitOperations(n: Int): Int {
            var t = Integer.highestOneBit(n)

            var result = 0

            var bit = 0

            while (t > 0) {
                bit = bit xor if (n and t > 0) 1 else 0
                result += bit * t
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

