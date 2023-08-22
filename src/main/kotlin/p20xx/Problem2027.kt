package p20xx

import util.expect

fun main() {
    class Solution {
        fun minimumMoves(s: String): Int {
            var index = 0
            var result = 0
            while (index < s.length) {
                when (s[index]) {
                    'X' -> {
                        result++
                        index += 3
                    }

                    'O' -> {
                        index++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumMoves(
            ""
        )
    }
}