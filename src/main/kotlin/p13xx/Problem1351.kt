package p13xx

import util.expect

fun main() {
    class Solution {
        fun countNegatives(grid: Array<IntArray>): Int {
            val n = grid[0].size
            var columnIndex = n - 1

            var result = 0
            grid.forEach {
                while (columnIndex >= 0 && it[columnIndex] < 0) {
                    columnIndex--
                }

                result += n - columnIndex - 1
            }

            return result
        }
    }

    expect {
        Solution().countNegatives(
            arrayOf(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
            )
        )
    }
}

