package p05xx

import util.expect

fun main() {
    class Solution {
        fun reverseWords(s: String): String {
            return s.split(" ").joinToString(" ") { it.reversed() }
        }
    }

    expect {
        Solution().reverseWords(
            "Let's take LeetCode contest"
        )
    }
}