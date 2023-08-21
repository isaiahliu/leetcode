package p17xx

import util.expect

fun main() {
    class Solution {
        fun getMaximumConsecutive(coins: IntArray): Int {
            var result = 1

            for (num in coins.sorted()) {
                if (num > result) {
                    break
                } else {
                    result += num
                }
            }

            return result
        }
    }

    expect {
        Solution().getMaximumConsecutive(
            intArrayOf(1, 4, 10, 3, 1)
        )
    }
}
