package p27xx

import util.expect

fun main() {
    class Solution {
        fun minimizedStringLength(s: String): Int {
            return s.toSet().size
        }
    }

    expect {
        Solution().minimizedStringLength(
            ""
        )
    }
}
