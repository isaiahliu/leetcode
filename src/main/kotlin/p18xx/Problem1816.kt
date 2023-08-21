package p18xx

import util.expect

fun main() {
    class Solution {
        fun truncateSentence(s: String, k: Int): String {
            return s.split(" ", limit = k + 1).take(k).joinToString(" ")
        }
    }

    expect {
        Solution().truncateSentence(
            "", 4
        )
    }
}
