package p12xx

import util.expect

fun main() {
    class Solution {
        fun countServers(grid: Array<IntArray>): Int {
            val rowCounts = IntArray(grid.size)
            val columnCounts = IntArray(grid[0].size)

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        rowCounts[r]++
                        columnCounts[c]++
                    }
                }
            }

            var result = 0

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        if (rowCounts[r] > 1 || columnCounts[c] > 1) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countServers(
            arrayOf()
        )
    }
}
