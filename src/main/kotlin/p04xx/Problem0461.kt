package p04xx

import util.expect

fun main() {
    class Solution {
        fun hammingDistance(x: Int, y: Int): Int {
            return Integer.bitCount(x xor y)
        }
    }

    expect {
        Solution().hammingDistance(1, 3)
    }
}