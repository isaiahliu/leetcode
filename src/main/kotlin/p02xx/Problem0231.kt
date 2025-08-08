package p02xx

import util.expect

fun main() {
    class Solution {
        fun isPowerOfTwo(n: Int): Boolean {
            return n.takeIf { it > 0 }?.countOneBits() == 1
        }
    }

    expect {
        Solution().isPowerOfTwo(1)
    }
}

