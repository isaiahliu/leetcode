package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun uniqueMorseRepresentations(words: Array<String>): Int {
            val dic = arrayOf(
                ".-",
                "-...",
                "-.-.",
                "-..",
                ".",
                "..-.",
                "--.",
                "....",
                "..",
                ".---",
                "-.-",
                ".-..",
                "--",
                "-.",
                "---",
                ".--.",
                "--.-",
                ".-.",
                "...",
                "-",
                "..-",
                "...-",
                ".--",
                "-..-",
                "-.--",
                "--.."
            )

            return words.map {
                it.map { dic[it - 'a'] }.joinToString("")
            }.distinct().size
        }
    }

    measureTimeMillis {
        Solution().uniqueMorseRepresentations(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}