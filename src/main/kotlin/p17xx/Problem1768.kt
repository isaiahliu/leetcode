package p17xx

import util.expect

fun main() {
    class Solution {
        fun mergeAlternately(word1: String, word2: String): String {
            val result = StringBuilder()

            repeat(word1.length.coerceAtLeast(word2.length)) {
                word1.getOrNull(it)?.also { result.append(it) }
                word2.getOrNull(it)?.also { result.append(it) }
            }

            return result.toString()
        }
    }

    expect {
        Solution().mergeAlternately(
            "", ""
        )
    }
}
