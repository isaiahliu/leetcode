package p25xx

import util.expect

fun main() {
    class Solution {
        fun deleteGreatestValue(grid: Array<IntArray>): Int {
            grid.forEach { it.sort() }

            return grid[0].indices.sumOf { i ->
                grid.maxOf { it[i] }
            }
        }
    }

    expect {
        Solution().deleteGreatestValue(
            arrayOf()
        )
    }
}

