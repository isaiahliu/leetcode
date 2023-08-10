package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumScore(a: Int, b: Int, c: Int): Int {
            return (a + b).coerceAtMost(b + c).coerceAtMost(a + c).coerceAtMost((a + b + c) / 2)
        }
    }

    measureTimeMillis {
        Solution().maximumScore(
            1, 2, 3
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
