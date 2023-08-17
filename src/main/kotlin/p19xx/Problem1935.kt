package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canBeTypedWords(text: String, brokenLetters: String): Int {
            return text.split(" ").count { it.none { it in brokenLetters } }
        }
    }

    measureTimeMillis {
        Solution().canBeTypedWords(
            "", ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}