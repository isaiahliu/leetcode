package p14xx

import util.expect

fun main() {
    class Solution {
        fun isPrefixOfWord(sentence: String, searchWord: String): Int {
            return " $sentence".split(" ").indexOfFirst {
                it.startsWith(searchWord)
            }
        }
    }

    expect {
        Solution().isPrefixOfWord(
            "burg", "bur"
        )

    }
}

