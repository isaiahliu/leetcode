package p24xx

import util.expect

fun main() {
    class Solution {
        fun appendCharacters(s: String, t: String): Int {
            var l1 = 0
            var l2 = 0

            while (l1 < s.length && l2 < t.length) {
                if (s[l1++] == t[l2]) {
                    l2++
                }
            }

            return t.length - l2
        }
    }

    expect {
        Solution().appendCharacters(
            "coaching", "coding"
        )
    }
}