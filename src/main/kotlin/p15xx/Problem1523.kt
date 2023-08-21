package p15xx

import util.expect

fun main() {
    class Solution {
        fun countOdds(low: Int, high: Int): Int {
            return (high - low + 1) / 2 + (low and high and 1)
        }
    }

    expect {
        Solution().countOdds(
            8, 10
        )
    }
}

