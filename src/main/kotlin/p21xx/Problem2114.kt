package p21xx

import util.expect

fun main() {
    class Solution {
        fun mostWordsFound(sentences: Array<String>): Int {
            return sentences.maxOf { it.count { it == ' ' } } + 1
        }
    }

    expect {
        Solution().mostWordsFound(
            arrayOf()
        )
    }
}