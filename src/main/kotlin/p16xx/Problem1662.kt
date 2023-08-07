package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
            return word1.joinToString("") == word2.joinToString("")
        }
    }

    measureTimeMillis {
        Solution().arrayStringsAreEqual(
            arrayOf(), arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

