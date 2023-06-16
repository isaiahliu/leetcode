package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfCuts(n: Int): Int {
            return when {
                n == 1 -> {
                    0
                }

                n % 2 == 1 -> {
                    n
                }

                else -> {
                    n / 2
                }
            }
        }
    }

    measureTimeMillis {
        Solution().numberOfCuts(
            8
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}