package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun orangesRotting(grid: Array<IntArray>): Int {
            val badOranges = hashSetOf<Pair<Int, Int>>()
            var goodsOrangeCount = 0

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    when (c) {
                        1 -> goodsOrangeCount++
                        2 -> badOranges.add(rowIndex to columnIndex)
                    }
                }
            }

            var minutes = 0
            while (badOranges.isNotEmpty() && goodsOrangeCount > 0) {
                badOranges.toSet().also { badOranges.clear() }.forEach { (r, c) ->
                    arrayOf(r to c - 1, r to c + 1, r - 1 to c, r + 1 to c).filter {
                        grid.getOrNull(it.first)?.getOrNull(it.second) == 1
                    }.forEach {
                        grid[it.first][it.second] = 2
                        goodsOrangeCount--

                        badOranges.add(it)
                    }
                }


                minutes++
            }

            return minutes.takeIf { goodsOrangeCount == 0 } ?: -1
        }
    }

    measureTimeMillis {
        Solution().orangesRotting(
            arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
