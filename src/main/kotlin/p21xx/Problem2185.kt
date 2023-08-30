package p21xx

import util.expect

fun main() {
    class Solution {
        fun prefixCount(words: Array<String>, pref: String): Int {
            return words.count { it.startsWith(pref) }
        }
    }

    expect {
        Solution().prefixCount(
            arrayOf(), ""
        )
    }
}