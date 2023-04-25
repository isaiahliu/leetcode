package p08xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun projectionArea(grid: Array<IntArray>): Int {
            val x = IntArray(grid.size)
            val y = IntArray(grid[0].size)
            var result = 0

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, h ->
                    result += h.sign

                    x[r] = x[r].coerceAtLeast(h)
                    y[c] = y[c].coerceAtLeast(h)
                }
            }

            result += x.sum() + y.sum()
            return result
        }
    }

    measureTimeMillis {
        Solution().projectionArea(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}