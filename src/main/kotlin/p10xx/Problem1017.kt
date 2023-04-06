package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun baseNeg2(n: Int): String {
            val result = StringBuilder()

            var sign = 1
            var t = n
            while (t != 0) {
                (t % 2).also {
                    result.insert(0, it)
                    t -= it * sign
                }

                t /= 2
                sign = -sign
            }

            return result.toString().ifEmpty { "0" }
        }
    }

    measureTimeMillis {
        Solution().baseNeg2(
            9
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}