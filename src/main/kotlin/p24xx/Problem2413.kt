package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun smallestEvenMultiple(n: Int): Int {
            return n shl (n % 2)
        }
    }

    measureTimeMillis {
        Solution().smallestEvenMultiple(
            5
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}