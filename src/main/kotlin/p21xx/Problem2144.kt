package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(cost: IntArray): Int {
            cost.sortDescending()

            var result = 0

            cost.forEachIndexed { index, i ->
                if (index % 3 < 2) {
                    result += i
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumCost(
            intArrayOf(1, 2, 3)
        )
    }
}