package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun getBiggestThree(grid: Array<IntArray>): IntArray {
            val sums = Array(grid.size) { r ->
                Array(grid[0].size) { c ->
                    (grid[r][c]).let {
                        intArrayOf(it, it)
                    }
                }
            }

            for (r in 1 until sums.size) {
                val row = sums[r]

                for (c in row.indices) {
                    sums[r - 1].getOrNull(c - 1)?.also {
                        row[c][0] += it[0]
                    }

                    sums[r - 1].getOrNull(c + 1)?.also {
                        row[c][1] += it[1]
                    }
                }
            }

            val result = TreeSet<Int>(compareByDescending { it })

            fun addNum(num: Int) {
                result.add(num)

                if (result.size > 3) {
                    result.pollLast()
                }
            }

            val validR = grid.indices
            val validC = grid[0].indices
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, num ->
                    addNum(num)

                    var size = 1
                    while (true) {
                        val (leftR, leftC) = rowIndex + size to columnIndex - size
                        val (rightR, rightC) = rowIndex + size to columnIndex + size
                        val (bottomR, bottomC) = rowIndex + size * 2 to columnIndex

                        if (leftR !in validR || rightR !in validR || bottomR !in validR || leftC !in validC || rightC !in validC || bottomC !in validC) {
                            break
                        }

                        val topLeft = sums[leftR - 1][leftC + 1][1] - sums[rowIndex][columnIndex][1]
                        val topRight = sums[rightR - 1][rightC - 1][0] - sums[rowIndex][columnIndex][0]
                        val bottomLeft = sums[bottomR - 1][bottomC - 1][0] - sums[leftR][leftC][0]
                        val bottomRight = sums[bottomR - 1][bottomC + 1][1] - sums[rightR][rightC][1]

                        addNum(num + grid[leftR][leftC] + grid[rightR][rightC] + grid[bottomR][bottomC] + topLeft + topRight + bottomLeft + bottomRight)

                        size++
                    }
                }
            }

            return result.toIntArray()
        }
    }

    expect {
        Solution().getBiggestThree(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9),
            )
        ).toList()
        Solution().getBiggestThree(
            arrayOf(
                intArrayOf(3, 4, 5, 1, 3),
                intArrayOf(3, 3, 4, 2, 3),
                intArrayOf(20, 30, 200, 40, 10),
                intArrayOf(1, 5, 5, 4, 1),
                intArrayOf(4, 3, 2, 2, 5)
            )
        ).toList()
    }
}
