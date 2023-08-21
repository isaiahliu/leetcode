package p13xx

import util.expect

fun main() {
    class Solution {
        fun numberOfSubstrings(s: String): Int {
            var result = 0

            val last = intArrayOf(-1, -1, -1)

            s.forEachIndexed { index, c ->
                last[c - 'a'] = index

                result += last.min() + 1
            }

            return result
        }
    }

    expect {
        Solution().numberOfSubstrings(
            "abcabc"
        )
    }
}

