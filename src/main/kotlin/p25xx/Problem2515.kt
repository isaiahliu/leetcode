package p25xx

import util.expect

fun main() {
    class Solution {
        fun closetTarget(words: Array<String>, target: String, startIndex: Int): Int {
            return (0..words.size / 2).firstOrNull {
                words[(startIndex + it).mod(words.size)] == target || words[(startIndex - it).mod(words.size)] == target
            } ?: -1
        }
    }

    expect {
        Solution().closetTarget(
            arrayOf(), "", 1
        )
    }
}