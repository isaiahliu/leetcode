package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximum69Number(num: Int): Int {
            var t = num

            var result = 0

            var times = 1
            while (t > 0) {
                if (t % 10 == 6) {
                    result = times
                }

                times *= 10
                t /= 10
            }

            return num + 3 * result
        }
    }

    measureTimeMillis {
        Solution().maximum69Number(
            99
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

