package p25xx

import util.expect

fun main() {
    class Solution {
        fun deleteGreatestValue(grid: Array<IntArray>): Int {
            grid.forEach { it.sort() }

            return grid[0].indices.map { i ->
                grid.map { it[i] }.max()
            }.sum()
        }
    }

    expect {
        Solution().deleteGreatestValue(
            arrayOf()
        )
    }
}

