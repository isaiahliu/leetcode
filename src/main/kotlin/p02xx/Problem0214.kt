package p02xx

import util.expect

fun main() {
    class Solution {
        fun shortestPalindrome(s: String): String {
            val reverseS = s.reversed()

            if (s == reverseS) {
                return s
            }

            var l = s.lastIndex

            while (true) {
                if (s.substring(0, l) == reverseS.substring(s.length - l)) {
                    break
                }

                l--
            }

            return reverseS.substring(0, s.length - l) + s
        }
    }

    expect {
        Solution().shortestPalindrome(
            "abcd"
        )
    }
}

