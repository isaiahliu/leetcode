package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
            val n = grid.size
            val rowMax = IntArray(n)
            val columnMax = IntArray(n)

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, height ->
                    rowMax[r] = rowMax[r].coerceAtLeast(height)
                    columnMax[c] = columnMax[c].coerceAtLeast(height)
                }
            }

            var result = 0
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, height ->
                    result += rowMax[r].coerceAtMost(columnMax[c]) - height
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxIncreaseKeepingSkyline(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}