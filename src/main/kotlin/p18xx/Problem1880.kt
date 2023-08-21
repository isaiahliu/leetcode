package p18xx

import util.expect

fun main() {
    class Solution {
        fun isSumEqual(firstWord: String, secondWord: String, targetWord: String): Boolean {
            fun String.charToNum(): Int {
                return fold(0) { num, ch ->
                    num * 10 + (ch - 'a')
                }
            }

            return firstWord.charToNum() + secondWord.charToNum() == targetWord.charToNum()
        }
    }

    expect {
        Solution().isSumEqual(
            "", "", ""
        )
    }
}
