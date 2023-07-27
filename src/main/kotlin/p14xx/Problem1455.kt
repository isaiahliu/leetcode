package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPrefixOfWord(sentence: String, searchWord: String): Int {
            return " $sentence".split(" ").indexOfFirst {
                it.startsWith(searchWord)
            }
        }
    }

    measureTimeMillis {
        Solution().isPrefixOfWord(
            "burg", "bur"
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

