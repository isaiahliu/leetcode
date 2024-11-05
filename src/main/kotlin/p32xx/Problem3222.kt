package p32xx

import util.expect

fun main() {
    class Solution {
        fun losingPlayer(x: Int, y: Int): String {
            return if (minOf(x, y / 4) % 2 == 1) "Alice" else "Bob"
        }
    }
    expect {
        Solution().losingPlayer(
            5, 2
        )
    }
}
