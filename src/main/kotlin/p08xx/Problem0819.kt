package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mostCommonWord(paragraph: String, banned: Array<String>): String {
            val bannedSet = banned.toSet()

            return paragraph.split("\\W+".toRegex()).map { it.lowercase() }
                .filter { it.isNotBlank() && it !in bannedSet }.groupingBy { it }
                .eachCount().entries.sortedWith(compareByDescending { it.value })
                .maxBy { it.value }.key
        }
    }

    measureTimeMillis {
        Solution().mostCommonWord(
            "", arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}