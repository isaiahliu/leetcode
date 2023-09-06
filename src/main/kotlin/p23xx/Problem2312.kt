package p23xx

import util.expect

fun main() {
    class Solution {
        fun sellingWood(m: Int, n: Int, prices: Array<IntArray>): Long {
            val priceMap = Array(m + 1) { LongArray(n + 1) }

            var minRow = m
            var minColumn = n
            prices.forEach { (row, column, price) ->
                priceMap[row][column] = price.toLong()

                minRow = minRow.coerceAtMost(row)
                minColumn = minColumn.coerceAtMost(column)
            }

            val cache = Array(m + 1) { LongArray(n + 1) { -1 } }
            fun dfs(row: Int, column: Int): Long {
                return when {
                    row == 0 || column == 0 -> 0
                    cache[row][column] >= 0 -> cache[row][column]
                    else -> {
                        var result = priceMap[row][column]

                        for (r in minRow until row - minRow) {
                            result = result.coerceAtLeast(dfs(r, column) + dfs(row - r, column))
                        }

                        for (c in minColumn until column - minColumn) {
                            result = result.coerceAtLeast(dfs(row, c) + dfs(row, column - c))
                        }

                        cache[row][column] = result
                        result
                    }
                }
            }

            return dfs(m, n)
        }
    }

    expect {
        Solution().sellingWood(
            9, 7, arrayOf(
                intArrayOf(1, 4, 2),
                intArrayOf(2, 2, 7),
                intArrayOf(2, 1, 3),
            )
        )
    }
}