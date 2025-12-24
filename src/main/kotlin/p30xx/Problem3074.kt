package p30xx

import util.expect

fun main() {
    class Solution {
        fun minimumBoxes(apple: IntArray, capacity: IntArray): Int {
            var sum = apple.sum()
            var result = 0
            capacity.sortDescending()

            while (sum > 0) {
                sum -= capacity[result++]
            }

            return result
        }
    }

    expect {
        Solution().minimumBoxes(
            intArrayOf(), intArrayOf()
        )
    }
}
