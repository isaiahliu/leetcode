package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun trailingZeroes(n: Int): Int {
            var result = 0
            var t = n / 5

            while (t > 0) {
                result += t
                t /= 5
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().trailingZeroes(30).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

