package p32xx

import util.expect

fun main() {
    class Solution {
        fun validSubstringCount(word1: String, word2: String): Long {
            var chars1 = IntArray(26)
            var chars2 = IntArray(26)

            var charKind = 0
            word2.forEach {
                val ch = it - 'a'
                if (chars2[ch]++ == 0) {
                    charKind++
                }
            }

            var result = 0L
            var left = 0
            var right = 0
            var matchKind = 0
            while (right < word1.length || charKind == matchKind) {
                if (charKind == matchKind) {
                    val ch = word1[left++] - 'a'

                    if (chars2[ch] > 0) {
                        if (chars1[ch]-- == chars2[ch]) {
                            matchKind--
                        }
                    }

                } else {
                    val ch = word1[right++] - 'a'

                    if (chars2[ch] > 0) {
                        if (++chars1[ch] == chars2[ch]) {
                            matchKind++
                        }
                    }
                }

                if (charKind == matchKind) {
                    result += word1.length - right + 1
                }
            }
            return result
        }
    }

    expect {
        Solution().validSubstringCount(
            "ddccdddccdddccccdddccdcdcd", "ddc"
        )
    }
}
