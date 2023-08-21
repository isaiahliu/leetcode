package p19xx

import util.expect

fun main() {
    class Solution {
        fun makeFancyString(s: String): String {
            return "(.)\\1{2,}".toRegex().replace(s, "$1$1")
        }
    }

    expect {
        Solution().makeFancyString(
            "leeeeeetcode"
        )
    }
}