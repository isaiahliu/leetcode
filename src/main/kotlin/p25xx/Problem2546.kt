package p25xx

import util.expect

fun main() {
    class Solution {
        fun makeStringsEqual(s: String, target: String): Boolean {
            return s.any { it == '1' } xor target.none { it == '1' }
        }
    }

    expect {
        Solution().makeStringsEqual(
            "", ""
        )
    }
}

