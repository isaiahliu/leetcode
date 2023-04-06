package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun baseNeg2(n: Int): String {
            val result = StringBuilder()

            var t = n
            while (t != 0) {
                (t % 2).let { it * it }.also {
                    result.insert(0, it)
                    t -= it
                }

                t /= -2
            }

            return result.toString().ifEmpty { "0" }
        }
    }

    measureTimeMillis {
        Solution().baseNeg2(
            3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}