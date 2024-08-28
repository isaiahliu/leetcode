package p31xx

import util.expect

fun main() {
    class Solution {
        fun satisfiesConditions(grid: Array<IntArray>): Boolean {
            return grid.indices.all { rowIndex ->
                val row = grid[rowIndex]
                row.indices.all { columnIndex ->
                    row[columnIndex] != row.getOrNull(columnIndex + 1) && grid.getOrNull(rowIndex + 1)?.get(columnIndex)?.takeIf { it != row[columnIndex] } == null
                }
            }
        }
    }

    expect {
        Solution().satisfiesConditions(
            arrayOf()
        )
    }
}
