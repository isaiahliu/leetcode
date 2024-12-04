package p32xx

import util.expect

fun main() {
    class Solution {
        fun checkTwoChessboards(coordinate1: String, coordinate2: String): Boolean {
            return (coordinate1 + coordinate2).sumOf { it.code } and 1 == 0
        }
    }

    expect {
        Solution().checkTwoChessboards(
            "a1", "c3"
        )
    }
}
