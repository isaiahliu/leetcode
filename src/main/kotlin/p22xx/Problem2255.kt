package p22xx

import util.expect

fun main() {
    class Solution {
        fun countPrefixes(words: Array<String>, s: String): Int {
            return words.count { s.startsWith(it) }
        }
    }

    expect {
        Solution().countPrefixes(
            arrayOf(
                ""
            ), ""
        )
    }
}