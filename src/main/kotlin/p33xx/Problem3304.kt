package p33xx

import util.expect

fun main() {
    class Solution {
        fun kthCharacter(k: Int): Char {
            return 'a' + (k - 1).countOneBits()
        }
    }

    expect {
        Solution().kthCharacter(
            10
        )
    }
}
