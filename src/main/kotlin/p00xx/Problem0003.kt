package p00xx

import util.expect

fun main() {
    class Solution {
        val firstChar = Char(0)

        operator fun IntArray.get(c: Char): Int {
            return this[c - firstChar]
        }

        operator fun IntArray.set(c: Char, value: Int) {
            this[c - firstChar] = value
        }

        fun lengthOfLongestSubstring(s: String): Int {
            val counts = IntArray(127)

            var startIndex = 0
            var endIndex = 0

            var maxLength = -1

            while (endIndex < s.length) {
                val end = s[endIndex]

                counts[end]++

                if (counts[end] > 1) {
                    do {
                        counts[s[startIndex++]]--
                    } while (counts[s[startIndex - 1]] < 1)
                } else {
                    maxLength = maxLength.coerceAtLeast(endIndex - startIndex)
                }

                endIndex++
            }

            return maxLength + 1
        }
    }

    expect {
        Solution().lengthOfLongestSubstring("")
    }
}

