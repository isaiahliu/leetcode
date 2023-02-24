package p02xx

fun main() {
    class Solution {
        fun shortestPalindrome(s: String): String {
            val reverseS = s.reversed()

            if (s == reverseS) {
                return s
            }

            var l = s.length - 1

            while (true) {
                if (s.substring(0, l) == reverseS.substring(s.length - l)) {
                    break
                }

                l--
            }

            return reverseS.substring(0, s.length - l) + s
        }
    }

    println(
        Solution().shortestPalindrome(
            "abcd"
        )
    )
}

