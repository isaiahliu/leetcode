package p11xx

import util.expect

fun main() {
    class Solution {
        fun longestDecomposition(text: String): Int {
            var result = 0

            var s = 0
            var r = text.lastIndex

            val left = StringBuilder()
            val right = StringBuilder()

            while (s < r) {
                left.append(text[s++])
                right.insert(0, text[r--])

                if (left.length == 1) {
                    result++
                }

                if (left.toString() == right.toString()) {
                    result++
                    left.clear()
                    right.clear()
                }
            }

            if (s == r && left.isEmpty()) {
                result++
            }

            return result
        }
    }

    expect {
        Solution().longestDecomposition(
            "ghiabcdefhelloadamhelloabcdefghi"
        )
    }
}