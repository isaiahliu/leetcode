package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numDifferentIntegers(word: String): Int {
            return word.split("\\D".toRegex()).filter { it.isNotEmpty() }.map { it.trimStart('0') }.distinct().size
        }
    }

    measureTimeMillis {
        Solution().numDifferentIntegers(
            "a123bc34d8ef34"
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
