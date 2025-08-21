package p31xx

import util.expect

fun main() {
    class Solution {
        fun minimumArea(grid: Array<IntArray>): Int {
            var minR = Int.MAX_VALUE
            var maxR = 0
            var minC = Int.MAX_VALUE
            var maxC = 0

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, v ->
                    if (v > 0) {
                        minR = minOf(minR, r)
                        maxR = maxOf(maxR, r)
                        minC = minOf(minC, c)
                        maxC = maxOf(maxC, c)
                    }
                }
            }

            return (maxR - minR + 1) * (maxC - minC + 1)
        }
    }

    expect {
        Solution().minimumArea(
            arrayOf()
        )
    }
}
