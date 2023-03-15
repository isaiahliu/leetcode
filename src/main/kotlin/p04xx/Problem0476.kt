package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findComplement(num: Int): Int {
            var result = 0

            var t = num
            var base = 1
            while (t > 0) {
                result += base * (1 - (t and 1))
                base *= 2

                t /= 2
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().findComplement(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}