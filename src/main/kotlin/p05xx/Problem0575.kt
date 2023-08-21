package p05xx

import util.expect

fun main() {
    class Solution {
        fun distributeCandies(candyType: IntArray): Int {
            return candyType.distinct().size.coerceAtMost(candyType.size / 2)
        }
    }

    expect {
        Solution().distributeCandies(
            intArrayOf(1, 1, 2, 3)
        )

    }
}