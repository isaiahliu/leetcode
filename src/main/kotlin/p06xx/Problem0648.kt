package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun replaceWords(dictionary: List<String>, sentence: String): String {
            val sortedDics = dictionary.sortedBy { it.length }
            return sentence.split(" ").map { word ->
                sortedDics.firstOrNull { word.startsWith(it) } ?: word
            }.joinToString(" ")
        }
    }

    measureTimeMillis {
        Solution().replaceWords(
            listOf(), ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}