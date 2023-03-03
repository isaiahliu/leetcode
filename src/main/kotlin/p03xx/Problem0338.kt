package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countBits(n: Int): IntArray {
            return IntArray(n + 1) {
                Integer.bitCount(it)
            }
        }
    }

    measureTimeMillis {
        Solution().countBits(
            50
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

