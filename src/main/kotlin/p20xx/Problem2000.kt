package p20xx

import util.expect

fun main() {
    class Solution {
        fun reversePrefix(word: String, ch: Char): String {
            return word.indexOf(ch).takeIf { it >= 0 }?.let {
                "${word.take(it + 1).reversed()}${word.drop(it + 1)}"
            } ?: word
        }
    }

    expect {
        Solution().reversePrefix(
            "", '1'
        )
    }
}