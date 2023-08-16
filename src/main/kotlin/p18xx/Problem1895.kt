package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestMagicSquare(grid: Array<IntArray>): Int {
            val sums = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    grid[r][c].let {
                        //rowSum, columnSum, topLeftSum, topRightSum
                        intArrayOf(it, it, it, it)
                    }
                }
            }

            sums.forEachIndexed { r, row ->
                row.forEachIndexed { c, sum ->
                    sums.getOrNull(r)?.getOrNull(c - 1)?.also { sum[0] += sums[r][c - 1][0] }
                    sums.getOrNull(r - 1)?.getOrNull(c)?.also { sum[1] += sums[r - 1][c][1] }
                    sums.getOrNull(r - 1)?.getOrNull(c - 1)?.also { sum[2] += sums[r - 1][c - 1][2] }
                    sums.getOrNull(r - 1)?.getOrNull(c + 1)?.also { sum[3] += sums[r - 1][c + 1][3] }
                }
            }

            for (target in grid.size.coerceAtMost(grid[0].size) downTo 2) {
                for (r in 0..sums.size - target) {
                    val row = sums[r]
                    for (c in 0..row.size - target) {
                        val set = hashSetOf<Int>()

                        repeat(target) {
                            set.add(
                                sums[r + it][c + target - 1][0] - (sums[r + it].getOrNull(c - 1)?.getOrNull(0) ?: 0)
                            )
                        }


                        repeat(target) {
                            set.add(
                                sums[r + target - 1][c + it][1] - (sums.getOrNull(r - 1)?.getOrNull(c + it)
                                    ?.getOrNull(1)
                                    ?: 0)
                            )
                        }

                        set.add(
                            sums[r + target - 1][c + target - 1][2] - (sums.getOrNull(r - 1)?.getOrNull(c - 1)
                                ?.getOrNull(2)
                                ?: 0)
                        )

                        set.add(
                            sums[r + target - 1][c][3] - (sums.getOrNull(r - 1)?.getOrNull(c + target)?.getOrNull(3)
                                ?: 0)
                        )

                        if (set.size == 1) {
                            return target
                        }
                    }
                }
            }

            return 1
        }
    }

    measureTimeMillis {
        Solution().largestMagicSquare(
            arrayOf(
                intArrayOf(2, 7, 6),
                intArrayOf(9, 5, 1),
                intArrayOf(4, 3, 8),
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
