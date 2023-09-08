package p23xx

import util.expect

fun main() {
    class Solution {
        fun removeStars(s: String): String {
            val result = StringBuilder()
            var count = 0
            for (i in s.indices.reversed()) {
                if (s[i] == '*') {
                    count++
                } else if (count > 0) {
                    count--
                } else {
                    result.insert(0, s[i])
                }
            }

            return "${"*".repeat(count)}$result"
        }
    }

    expect {
        Solution().removeStars(
            ""
        )
    }
}