package p21xx

import util.expect

fun main() {
    class Solution {
        fun checkString(s: String): Boolean {
            return s.trimStart('a').trimEnd('b').isEmpty()
        }
    }

    expect {
        Solution().checkString(
            ""
        )
    }
}