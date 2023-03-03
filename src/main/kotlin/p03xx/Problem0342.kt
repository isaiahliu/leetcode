package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPowerOfFour(n: Int): Boolean {
            return n > 0 && 0x40000000 % n == 0 && 0x55555555 and n == n
        }
    }

    measureTimeMillis {
        Solution().isPowerOfFour(
            16
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

