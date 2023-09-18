package p24xx

import util.expect

fun main() {
    class Solution {
        fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
            val rowOnes = IntArray(grid.size)
            val columnOnes = IntArray(grid[0].size)

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    rowOnes[r] += num
                    columnOnes[c] += num
                }
            }

            return Array(grid.size) { r ->
                IntArray(grid[r].size) { c ->
                    rowOnes[r] * 2 - grid.size + columnOnes[c] * 2 - grid[0].size
                }
            }
        }
    }

    expect {
        Solution().onesMinusZeros(
            arrayOf(
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(1, 0),
                intArrayOf(0, 4),
                intArrayOf(0, 5),
                intArrayOf(4, 6)
            )
        )
    }
}
