package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getNoZeroIntegers(n: Int): IntArray {
            fun Int.valid(): Boolean {
                return '0' !in toString()
            }

            var result = 1

            while (true) {
                if (result.valid() && (n - result).valid()) {
                    return intArrayOf(result, n - result)
                }

                result++
            }
        }
    }

    measureTimeMillis {
        Solution().getNoZeroIntegers(
            1000
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

