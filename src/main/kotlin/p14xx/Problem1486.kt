package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun xorOperation(n: Int, start: Int): Int {
            var result = 0

            repeat(n) {
                result = result xor (start + it * 2)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().xorOperation(
            5, 1
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

