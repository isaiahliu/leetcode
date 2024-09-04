package p28xx

import util.expect

fun main() {
    class Solution {
        fun countWays(nums: List<Int>): Int {
            var result = 0

            var previousMatch = true
            (nums.sortedBy { it } + Int.MAX_VALUE).forEachIndexed { index, num ->
                if (previousMatch && num > index) {
                    result++
                }
                previousMatch = index >= num
            }

            return result
        }
    }

    expect {
        Solution().countWays(
            listOf(1, 1)
        )
    }
}
