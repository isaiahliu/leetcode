package p20xx

import util.expect

fun main() {
    class Solution {
        fun checkAlmostEquivalent(word1: String, word2: String): Boolean {
            val counts = IntArray(26)

            word1.forEach { counts[it - 'a']++ }
            word2.forEach { counts[it - 'a']-- }

            return counts.all { it in -3..3 }
        }
    }

    expect {
        Solution().checkAlmostEquivalent(
            "", ""
        )
    }
}