package p26xx

import util.expect

fun main() {
    class Solution {
        fun makeSmallestPalindrome(s: String): String {
            val chars = s.toCharArray()

            var left = 0
            var right = chars.lastIndex

            while (left < right) {
                val min = minOf(chars[left], chars[right])

                chars[left++] = min
                chars[right--] = min
            }

            return chars.concatToString()
        }
    }

    expect {
        Solution().makeSmallestPalindrome(
            ""
        )
    }
}