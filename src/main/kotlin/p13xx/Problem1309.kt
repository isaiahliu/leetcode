package p13xx

import util.expect

fun main() {
    class Solution {
        fun freqAlphabets(s: String): String {
            val result = StringBuilder()

            var index = s.lastIndex

            while (index >= 0) {
                var charIndex = 0
                if (s[index] == '#') {
                    index--
                    charIndex = s[index] - '0'
                    index--
                    charIndex += (s[index] - '0') * 10
                } else {
                    charIndex = s[index] - '0'
                }
                result.insert(0, 'a' + charIndex - 1)
                index--
            }

            return result.toString()
        }
    }

    expect {
        Solution().freqAlphabets(
            ""
        )
    }
}

