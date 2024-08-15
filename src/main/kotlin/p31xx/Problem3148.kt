package p31xx

import util.expect

fun main() {
    class Solution {
        fun maxScore(grid: List<List<Int>>): Int {
            val columnMins = IntArray(grid[0].size) {
                999999
            }

            var result = Int.MIN_VALUE
            grid.forEach { row ->
                var min = 999999
                row.forEachIndexed { c, n ->
                    result = maxOf(result, n - minOf(min, columnMins[c]))

                    columnMins[c] = minOf(columnMins[c], n)

                    min = minOf(min, columnMins[c])
                }
            }

            return result
        }
    }

    expect {
        Solution().maxScore(
            listOf()
        )
    }
}
