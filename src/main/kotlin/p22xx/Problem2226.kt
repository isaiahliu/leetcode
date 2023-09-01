package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumCandies(candies: IntArray, k: Long): Int {
            var min = 1
            var max = candies.max()

            var result = 0

            while (min <= max) {
                val mid = (min + max) / 2

                if (candies.sumOf { it.toLong() / mid } >= k) {
                    result = mid
                    min = mid + 1
                } else {
                    max = mid - 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumCandies(
            intArrayOf(), 1L
        )
    }
}