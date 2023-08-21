package p18xx

import util.expect

fun main() {
    class Solution {
        fun countGoodSubstrings(s: String): Int {
            if (s.length < 3) {
                return 0
            }

            val counts = IntArray(26)
            var dupCount = 0

            fun addChar(char: Char) {
                if (counts[char - 'a'] == 1) {
                    dupCount++
                }

                counts[char - 'a']++
            }

            fun removeChar(char: Char) {
                if (counts[char - 'a'] == 2) {
                    dupCount--
                }

                counts[char - 'a']--
            }

            repeat(3) {
                addChar(s[it])
            }

            var result = 0

            if (dupCount == 0) {
                result++
            }

            for (i in 3 until s.length) {
                removeChar(s[i - 3])
                addChar(s[i])

                if (dupCount == 0) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().countGoodSubstrings(
            "aababcabc"
        )
    }
}
