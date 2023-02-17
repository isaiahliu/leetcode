package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largest1BorderedSquare(grid: Array<IntArray>): Int {
            var maxBorderLength = 0

            val sums = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    intArrayOf(grid[r][c], grid[r][c])
                }
            }

            sums.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, n ->
                    if (n[0] > 0) {
                        sums.getOrNull(rowIndex)?.getOrNull(columnIndex - 1)?.also {
                            n[0] += it[0]
                        }

                        sums.getOrNull(rowIndex - 1)?.getOrNull(columnIndex)?.also {
                            n[1] += it[1]
                        }


                        for (length in n[0].coerceAtMost(n[1]) downTo maxBorderLength + 1) {
                            if (sums[rowIndex - length + 1][columnIndex][0] >= length && sums[rowIndex][columnIndex - length + 1][1] >= length) {
                                maxBorderLength = length
                                break
                            }
                        }
                    }
                }
            }

            return maxBorderLength * maxBorderLength
        }
    }

    measureTimeMillis {
        Solution().largest1BorderedSquare(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1)
            )
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

