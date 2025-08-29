package p30xx

import util.expect

fun main() {
    class Solution {
        fun flowerGame(n: Int, m: Int): Long {
            return n.toLong() * m / 2
        }
    }

    expect {
        Solution().flowerGame(
            1, 2
        )
    }
}
