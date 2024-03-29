package p05xx

import util.expect

fun main() {
    class Solution {
        fun findLUSlength(a: String, b: String): Int {
            if (a == b) {
                return -1
            }

            return a.length.coerceAtLeast(b.length)
        }
    }

    expect {
        Solution().findLUSlength("ABC", "")
    }
}