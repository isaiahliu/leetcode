package p19xx

import util.expect

fun main() {
    class Solution {
        fun numOfStrings(patterns: Array<String>, word: String): Int {
            return patterns.count { it in word }
        }
    }

    expect {
        Solution().numOfStrings(
            arrayOf(), ""
        )
    }
}