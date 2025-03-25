package p27xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun differenceOfDistinctValues(grid: Array<IntArray>): Array<IntArray> {
            val counts = Array(grid.size) {
                Array(grid[it].size) {
                    arrayOf(hashSetOf<Int>(), hashSetOf<Int>())
                }
            }

            for (r in grid.indices) {
                for (c in grid[r].indices) {
                    grid.getOrNull(r - 1)?.getOrNull(c - 1)?.also {
                        counts[r][c][0] += it
                        counts[r][c][0] += counts[r - 1][c - 1][0]
                    }

                    grid.getOrNull(grid.lastIndex - r + 1)?.getOrNull(grid[0].lastIndex - c + 1)?.also {
                        counts[grid.lastIndex - r][grid[0].lastIndex - c][1] += it
                        counts[grid.lastIndex - r][grid[0].lastIndex - c][1] += counts[grid.lastIndex - r + 1][grid[0].lastIndex - c + 1][1]
                    }
                }
            }

            return Array(grid.size) { r ->
                IntArray(grid[r].size) { c ->
                    (counts[r][c][0].size - counts[r][c][1].size).absoluteValue
                }
            }
        }
    }

    expect {
        Solution().differenceOfDistinctValues(
            arrayOf()
        )
    }
}
