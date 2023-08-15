package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkIfPangram(sentence: String): Boolean {
            return sentence.toSet().size == 26
        }
    }

    measureTimeMillis {
        Solution().checkIfPangram(
            "thequickbrownfoxjumpsoverthelazydog"
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
