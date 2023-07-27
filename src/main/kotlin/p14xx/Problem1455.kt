package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPrefixOfWord(sentence: String, searchWord: String): Int {
            return sentence.split(" ").indexOfFirst {
                it.startsWith(searchWord)
            }.takeIf { it >= 0 }?.let { it + 1 } ?: -1
        }
    }

    measureTimeMillis {
        Solution().isPrefixOfWord(
            "", ""
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

