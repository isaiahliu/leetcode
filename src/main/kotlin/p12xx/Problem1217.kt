package p12xx

import util.expect

fun main() {
    class Solution {
        fun minCostToMoveChips(position: IntArray): Int {
            val counts = intArrayOf(0, 0)

            position.forEach {
                counts[it % 2]++
            }

            return counts.min()
        }
    }

    expect {
        Solution().minCostToMoveChips(
            intArrayOf()
        )
    }
}
