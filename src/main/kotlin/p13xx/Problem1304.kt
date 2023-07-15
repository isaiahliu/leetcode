package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumZero(n: Int): IntArray {
            return IntArray(n) {
                when {
                    n % 2 > 0 -> it - n / 2
                    it % 2 == 0 -> it / 2 + 1
                    else -> -it / 2 - 1
                }
            }
        }
    }

    measureTimeMillis {
        Solution().sumZero(
            5
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

