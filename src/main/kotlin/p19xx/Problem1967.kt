package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numOfStrings(patterns: Array<String>, word: String): Int {
            return patterns.count { it in word }
        }
    }

    measureTimeMillis {
        Solution().numOfStrings(
            arrayOf(), ""
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}