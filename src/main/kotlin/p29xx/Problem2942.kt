package p29xx

import util.expect

fun main() {
    class Solution {
        fun findWordsContaining(words: Array<String>, x: Char): List<Int> {
            return words.indices.filter { x in words[it] }
        }
    }

    expect {
        Solution().findWordsContaining(
            arrayOf(), '1'
        )
    }
}
