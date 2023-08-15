package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumBase(n: Int, k: Int): Int {
            var t = n
            var result = 0

            while (t > 0) {
                result += t % k
                t /= k
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().sumBase(
            34, 6
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
