package p16xx

import util.expect

fun main() {
    class Solution {
        fun countConsistentStrings(allowed: String, words: Array<String>): Int {
            return allowed.toSet().let { allowedSet -> words.count { it.all { it in allowedSet } } }
        }
    }

    expect {
        Solution().countConsistentStrings(
            "", arrayOf()
        )
    }
}

