package p18xx

import util.expect

fun main() {
    class Solution {
        fun numDifferentIntegers(word: String): Int {
            return word.split("\\D".toRegex()).filter { it.isNotEmpty() }.map { it.trimStart('0') }.distinct().size
        }
    }

    expect {
        Solution().numDifferentIntegers(
            "a123bc34d8ef34"
        )
    }
}
