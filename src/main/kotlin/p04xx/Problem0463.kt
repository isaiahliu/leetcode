package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun islandPerimeter(grid: Array<IntArray>): Int {
            var result = 0
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num == 1) {
                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                            if (grid.getOrNull(it.first)?.getOrNull(it.second)?.takeIf { it == 1 } == null) {
                                result++
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().islandPerimeter(
            arrayOf(
                intArrayOf(0, 1, 0, 0),
                intArrayOf(1, 1, 1, 0),
                intArrayOf(0, 1, 0, 0),
                intArrayOf(1, 1, 0, 0),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}