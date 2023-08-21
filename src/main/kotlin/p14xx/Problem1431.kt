package p14xx

import util.expect

fun main() {
    class Solution {
        fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
            val max = candies.max()

            return candies.map {
                it + extraCandies >= max
            }
        }
    }

    expect {
        Solution().kidsWithCandies(
            intArrayOf(), 19
        )
    }
}

