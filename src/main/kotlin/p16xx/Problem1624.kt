package p16xx

import util.expect

fun main() {
    class Solution {
        fun maxLengthBetweenEqualCharacters(s: String): Int {
            val pos = IntArray(26) { -1 }

            var result = -1
            s.forEachIndexed { index, c ->
                if (pos[c - 'a'] < 0) {
                    pos[c - 'a'] = index
                }

                result = result.coerceAtLeast(index - pos[c - 'a'] - 1)
            }

            return result
        }
    }

    expect {
        Solution().maxLengthBetweenEqualCharacters(
            ""
        )
    }
}