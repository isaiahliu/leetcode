package p23xx

import util.expect

fun main() {
    class Solution {
        fun bestHand(ranks: IntArray, suits: CharArray): String {
            val maxRank by lazy { ranks.toList().groupingBy { it }.eachCount().values.max() }
            return when {
                suits.distinct().size == 1 -> "Flush"
                maxRank >= 3 -> "Three of a Kind"
                maxRank == 2 -> "Pair"
                else -> "High Card"
            }
        }
    }

    expect {
        Solution().bestHand(
            intArrayOf(), charArrayOf()
        )
    }
}

