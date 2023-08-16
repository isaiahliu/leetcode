package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isSumEqual(firstWord: String, secondWord: String, targetWord: String): Boolean {
            fun String.charToNum(): Int {
                return fold(0) { num, ch ->
                    num * 10 + (ch - 'a')
                }
            }

            return firstWord.charToNum() + secondWord.charToNum() == targetWord.charToNum()
        }
    }

    measureTimeMillis {
        Solution().isSumEqual(
            "", "", ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
