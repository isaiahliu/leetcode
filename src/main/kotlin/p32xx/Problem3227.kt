package p32xx

import util.expect

fun main() {
    class Solution {
        fun doesAliceWin(s: String): Boolean {
            return "aeiou".toSet().let { vowels ->
                s.any { it in vowels }
            }
        }
    }

    expect {
        Solution().doesAliceWin("")
    }
}
