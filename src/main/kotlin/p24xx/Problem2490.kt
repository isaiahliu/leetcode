package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isCircularSentence(sentence: String): Boolean {
            sentence.forEachIndexed { index, c ->
                if (c == ' ' && sentence[index - 1] != sentence[index + 1]) {
                    return false
                }
            }

            return sentence[0] != sentence[sentence.lastIndex]
        }
    }

    measureTimeMillis {
        Solution().isCircularSentence(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}