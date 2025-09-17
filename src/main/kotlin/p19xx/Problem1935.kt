package p19xx

import util.expect

fun main() {
    class Solution {
        fun canBeTypedWords(text: String, brokenLetters: String): Int {
            var result = 0
            val brokenSet = brokenLetters.toSet()
            var length = 0
            text.forEach {
                when {
                    it in brokenSet -> length = Int.MIN_VALUE
                    it != ' ' -> length++
                    length > 0 -> {
                        result++
                        length = 0
                    }

                    else -> length = 0
                }
            }

            if (length > 0) {
                result++
            }

            return result
        }
    }

    expect {
        Solution().canBeTypedWords(
            "", ""
        )
    }
}