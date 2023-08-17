package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun areOccurrencesEqual(s: String): Boolean {
            return s.groupingBy { it }.eachCount().values.distinct().size == 1
        }
    }

    measureTimeMillis {
        Solution().areOccurrencesEqual(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}