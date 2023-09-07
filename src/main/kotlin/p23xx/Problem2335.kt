package p23xx

import util.expect

fun main() {
    class Solution {
        fun fillCups(amount: IntArray): Int {
            val max = amount.max()
            var sum = amount.sum()

            return if (max >= sum - max) {
                max
            } else {
                sum / 2 + sum % 2
            }
        }
    }

    expect {
        Solution().fillCups(
            intArrayOf(1, 4, 2)
        )
    }
}