package p33xx

import util.expect

fun main() {
    class Solution {
        fun isBalanced(num: String): Boolean {
            return num.mapIndexed { index, ch -> (ch - '0') * (1 - (index and 1 shl 1)) }.sum() == 0
        }
    }

    expect {
        Solution().isBalanced(
            "12122121"
        )
    }
}
