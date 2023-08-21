package p18xx

import util.expect

fun main() {
    class Solution {
        fun sortSentence(s: String): String {
            return s.split(" ").map { it.dropLast(1) to it.takeLast(1) }.sortedBy { it.second }
                .joinToString(" ") { it.first }
        }
    }

    expect {
        Solution().sortSentence(
            ""
        )

    }
}
