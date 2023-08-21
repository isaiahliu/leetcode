package p16xx

import util.expect

fun main() {
    class Solution {
        fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
            return word1.joinToString("") == word2.joinToString("")
        }
    }

    expect {
        Solution().arrayStringsAreEqual(
            arrayOf(), arrayOf()
        )
    }
}

