package p20xx

import util.expect

fun main() {
    class Solution {
        fun countValidWords(sentence: String): Int {
            val regex = "(?=.)[a-z]*([a-z]-[a-z])?[a-z]*[!\\.,]?".toRegex()

            return sentence.split(" ").count { regex.matches(it) }
        }
    }

    expect {
        Solution().countValidWords(
            ""
        )
    }
}