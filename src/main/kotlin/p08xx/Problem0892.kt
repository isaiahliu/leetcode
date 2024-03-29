package p08xx

import util.expect

fun main() {
    class Solution {
        fun surfaceArea(grid: Array<IntArray>): Int {
            var result = 0

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, height ->
                    if (height > 0) {
                        result += 2 + height * 4

                        grid.getOrNull(r - 1)?.getOrNull(c)?.also {
                            result -= it.coerceAtMost(height) * 2
                        }

                        grid.getOrNull(r)?.getOrNull(c - 1)?.also {
                            result -= it.coerceAtMost(height) * 2
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().surfaceArea(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            )
        )
    }
}