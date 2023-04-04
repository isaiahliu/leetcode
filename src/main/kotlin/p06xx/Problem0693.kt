package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun hasAlternatingBits(n: Int): Boolean {
            var last = 1 - n % 2

            var t = n
            while (t > 0) {
                if (last + t % 2 != 1) {
                    return false
                }

                last = t % 2
                t /= 2
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().hasAlternatingBits(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}