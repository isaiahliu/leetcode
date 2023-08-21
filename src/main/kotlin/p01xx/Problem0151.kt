package p01xx

import util.expect

fun main() {
    class Solution {
        fun reverseWords(s: String): String {
            return s.split(" ").filter { it.isNotEmpty() }.reversed().joinToString(" ")
        }
    }

    expect {
        Solution().reverseWords(
            ""
        )
    }
}

