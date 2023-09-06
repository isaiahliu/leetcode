package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun sellingWood(m: Int, n: Int, prices: Array<IntArray>): Long {
            val rowColumns = TreeMap<Int, TreeMap<Int, Long>>()
            val columnRows = TreeMap<Int, TreeMap<Int, Long>>()

            prices.forEach { (row, column, price) ->
                rowColumns.computeIfAbsent(row) { TreeMap() }[column] = price.toLong()
                columnRows.computeIfAbsent(column) { TreeMap() }[row] = price.toLong()
            }

            val cache = Array(m + 1) { LongArray(n + 1) { -1 } }
            fun dfs(row: Int, column: Int): Long {
                return when {
                    row == 0 || column == 0 -> 0
                    cache[row][column] >= 0 -> cache[row][column]
                    else -> {
                        var result = rowColumns[row]?.get(column) ?: 0L

                        for (r in rowColumns.keys) {
                            if (r < row) {
                                result = result.coerceAtLeast(dfs(r, column) + dfs(row - r, column))
                            } else {
                                break
                            }
                        }

                        for (c in columnRows.keys) {
                            if (c < column) {
                                result = result.coerceAtLeast(dfs(row, c) + dfs(row, column - c))
                            } else {
                                break
                            }
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