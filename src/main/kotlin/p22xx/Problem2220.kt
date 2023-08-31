package p22xx

import util.expect

fun main() {
    class Solution {
        fun minBitFlips(start: Int, goal: Int): Int {
            return (start xor goal).countOneBits()
        }
    }

    expect {
        Solution().minBitFlips(
            10, 7
        )
    }
}