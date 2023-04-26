package p08xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().surfaceArea(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}