package p25xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun checkValidGrid(grid: Array<IntArray>): Boolean {
            if (grid[0][0] != 0) {
                return false
            }

            val pos = Array(grid.size * grid.size) { 0 to 0 }
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    pos[num] = r to c
                }
            }

            for (i in 1 until pos.size) {
                val (r1, c1) = pos[i - 1]
                val (r2, c2) = pos[i]

                if (((r1 - r2) * (c1 - c2)).absoluteValue != 2) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().checkValidGrid(
            arrayOf()
        )
    }
}