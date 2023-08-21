package p23xx

import util.expect

fun main() {
    class Solution {
        fun largestLocal(grid: Array<IntArray>): Array<IntArray> {
            val result = Array(grid.size - 2) {
                IntArray(grid[it].size - 2)
            }

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, num ->
                    for (r in rowIndex - 2..rowIndex) {
                        for (c in columnIndex - 2..columnIndex) {
                            result.getOrNull(r)?.getOrNull(c)?.also {
                                result[r][c] = it.coerceAtLeast(num)
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().largestLocal(
            emptyArray()
        )
    }
}

