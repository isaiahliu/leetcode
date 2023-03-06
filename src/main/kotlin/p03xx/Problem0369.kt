package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getSum(a: Int, b: Int): Int {
            val x = a xor b
            val y = a and b

            return if (y == 0) {
                x
            } else {
                getSum(x, y shl 1)
            }
        }
    }

    measureTimeMillis {
        Solution().getSum(
            1, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

