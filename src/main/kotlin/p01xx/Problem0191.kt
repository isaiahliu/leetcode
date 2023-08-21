package p01xx

import util.expect

fun main() {
    class Solution {
        fun hammingWeight(n: Int): Int {
            return Integer.bitCount(n)
        }
    }

    expect {
        Solution().hammingWeight(
            -3
        )
    }
}

