package p06xx

import util.expect

fun main() {
    class Solution {
        fun replaceWords(dictionary: List<String>, sentence: String): String {
            val sortedDics = dictionary.sortedBy { it.length }
            return sentence.split(" ").map { word ->
                sortedDics.firstOrNull { word.startsWith(it) } ?: word
            }.joinToString(" ")
        }
    }

    expect {
        Solution().replaceWords(
            listOf(), ""
        )
    }
}