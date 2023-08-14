package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun truncateSentence(s: String, k: Int): String {
            return s.split(" ", limit = k + 1).take(k).joinToString(" ")
        }
    }

    measureTimeMillis {
        Solution().truncateSentence(
            "", 4
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
