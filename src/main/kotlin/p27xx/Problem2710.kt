package p27xx

import util.expect

fun main() {
    class Solution {
        fun removeTrailingZeros(num: String): String {
            return num.trimEnd('0')
        }
    }

    expect {
        Solution().removeTrailingZeros(
            ""
        )
    }
}
