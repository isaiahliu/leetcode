package p02xx

import util.expect

fun main() {
    class Solution {
        fun canWinNim(n: Int): Boolean {
            return n % 4 != 0
        }
    }

    expect {
        Solution().canWinNim(4)
    }
}

