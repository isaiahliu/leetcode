package p24xx

import util.expect

fun main() {
    class Solution {
        fun isCircularSentence(sentence: String): Boolean {
            sentence.forEachIndexed { index, c ->
                if (c == ' ' && sentence[index - 1] != sentence[index + 1]) {
                    return false
                }
            }

            return sentence[0] != sentence[sentence.lastIndex]
        }
    }

    expect {
        Solution().isCircularSentence(
            ""
        )
    }
}