package p19xx

import util.expect

fun main() {
    class Solution {
        fun canBeTypedWords(text: String, brokenLetters: String): Int {
            return text.split(" ").count { it.none { it in brokenLetters } }
        }
    }

    expect {
        Solution().canBeTypedWords(
            "", ""
        )
    }
}