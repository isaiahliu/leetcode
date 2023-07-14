package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sequentialDigits(low: Int, high: Int): List<Int> {
            val result = arrayListOf<Int>()
            (low.toString().length..high.toString().length.coerceAtMost(9)).forEach { length ->
                var t = (1..length).joinToString("") { it.toString() }.toInt()

                if (t in low..high) {
                    result.add(t)
                }

                val delta = "1".repeat(length).toInt()
                repeat(9 - length) {
                    t += delta

                    if (t in low..high) {
                        result.add(t)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().sequentialDigits(
            1, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
