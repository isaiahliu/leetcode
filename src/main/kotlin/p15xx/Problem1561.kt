package p15xx

import util.expect

fun main() {
    class Solution {
        fun maxCoins(piles: IntArray): Int {
            return piles.sorted().let { (piles.size / 3 until piles.size step 2).map { index -> it[index] }.sum() }
        }
    }

    expect {
        Solution().maxCoins(
            intArrayOf(3, 1)
        )
    }
}

