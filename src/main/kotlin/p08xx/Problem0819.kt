package p08xx

import util.expect

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

    expect {
        Solution().mostCommonWord(
            "", arrayOf()
        )
    }
}