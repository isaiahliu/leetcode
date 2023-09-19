package plcp

import util.expect

fun main() {
    class Solution {
        fun minCount(coins: IntArray): Int {
            return coins.sumOf {
                it / 2 + it % 2
            }
        }
    }

    expect {
        Solution().minCount(
            intArrayOf(1, 10, 1),
        )
    }
}
