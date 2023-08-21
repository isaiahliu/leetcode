package p02xx

import util.expect

fun main() {
    class Solution {
        fun isPowerOfTwo(n: Int): Boolean {
            if (n < 0) {
                return false
            }
            return Integer.bitCount(n) == 1
        }
    }

    expect {
        Solution().isPowerOfTwo(1)
    }
}

