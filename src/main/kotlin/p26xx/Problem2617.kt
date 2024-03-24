package p26xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumVisitedCells(grid: Array<IntArray>): Int {
            val dpRows = TreeSet<Int>()
            dpRows.addAll(grid.indices)

            val dpColumns = Array(grid.size) {
                TreeSet<Int>().also {
                    it.addAll(grid[0].indices)
                }
            }

            fun pop(r: Int, c: Int) {
                dpColumns[r].also {
                    it -= c

                    if (it.isEmpty()) {
                        dpRows -= r
                    }
                }
            }

            pop(0, 0)

            val current = hashSetOf(0 to 0)

            var result = 0

            while (current.isNotEmpty()) {
                result++

                current.toSet().also { current.clear() }.forEach { (r, c) ->
                    if (r == grid.lastIndex && c == grid[0].lastIndex) {
                        return result
                    }

                    dpRows.subSet(r, false, minOf(grid.lastIndex, r + grid[r][c]), true)
                        .filter { c in dpColumns[it] }.forEach {
                            current += it to c
                            pop(it, c)
                        }

                    dpColumns[r].subSet(c, false, minOf(grid[0].lastIndex, c + grid[r][c]), true).toSet().forEach {
                        current += r to it
                        pop(r, it)
                    }
                }
            }

            return -1
        }
    }

    expect {
        Solution().minimumVisitedCells(
            arrayOf()
        )
    }
}
