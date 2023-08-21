package p08xx

import util.expect

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

    expect {
        Solution().uniqueMorseRepresentations(
            arrayOf()
        )
    }
}