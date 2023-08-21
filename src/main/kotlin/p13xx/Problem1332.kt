package p13xx

import util.expect

fun main() {
    class Solution {
        fun removePalindromeSub(s: String): Int {
            return when {
                s.isEmpty() -> 0
                s == s.reversed() -> 1
                else -> 2
            }
        }
    }

    expect {
        Solution().removePalindromeSub(
            "ababa"
        )
    }
}

