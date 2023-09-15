package plcp

import util.expect

fun main() {
    class Solution {
        fun giveGem(gem: IntArray, operations: Array<IntArray>): Int {
            operations.forEach { (from, to) ->
                val half = gem[from] / 2
                gem[from] -= half
                gem[to] += half
            }

            return gem.max() - gem.min()
        }
    }

    expect {
        Solution().giveGem(
            intArrayOf(),
            arrayOf(
                intArrayOf(1, 10, 1),
                intArrayOf(2, 9, 1),
                intArrayOf(3, 8, 1),
                intArrayOf(4, 7, 1),
                intArrayOf(5, 6, 1),
            )
        )
    }
}
