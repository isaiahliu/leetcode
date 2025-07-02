package p33xx

import util.expect

fun main() {
    class Solution {
        fun kthCharacter(k: Int): Char {
            return 'a' + (k - 1).countOneBits() % 26
        }
    }

    expect {
        Solution().kthCharacter(
            10
        )
    }
}
