package p27xx

import util.expect

fun main() {
    class Solution {
        fun splitWordsBySeparator(words: List<String>, separator: Char): List<String> {
            return words.map { it.split(separator).filter { it.isNotEmpty() } }.flatten()
        }
    }

    expect {
        Solution().splitWordsBySeparator(
            listOf(), ' '
        )
    }
}
