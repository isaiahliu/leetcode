package p11xx

import util.expect

fun main() {
    class Solution {
        fun maxNumberOfBalloons(text: String): Int {
            val counts = text.groupingBy { it }.eachCount()

            return (counts['b'] ?: 0).coerceAtMost(counts['a'] ?: 0).coerceAtMost((counts['l'] ?: 0) / 2)
                .coerceAtMost((counts['o'] ?: 0) / 2).coerceAtMost(counts['n'] ?: 0)
        }
    }

    expect {
        Solution().maxNumberOfBalloons(
            ""
        )
    }
}