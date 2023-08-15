package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortSentence(s: String): String {
            return s.split(" ").map { it.dropLast(1) to it.takeLast(1) }.sortedBy { it.second }
                .joinToString(" ") { it.first }
        }
    }

    measureTimeMillis {
        Solution().sortSentence(
            ""
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
