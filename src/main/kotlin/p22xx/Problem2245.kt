package p22xx

import util.expect

fun main() {
    class Solution {
        fun maxTrailingZeros(grid: Array<IntArray>): Int {
            val sums = Array(grid.size) { r ->
                Array(grid[r].size) { c ->
                    var t = grid[r][c]

                    var count2 = 0
                    var count5 = 0

                    while (t % 2 == 0) {
                        count2++
                        t /= 2
                    }


                    while (t % 5 == 0) {
                        count5++
                        t /= 5
                    }

                    arrayOf(
                        intArrayOf(count2, count5),//top
                        intArrayOf(count2, count5),//left
                        intArrayOf(count2, count5),//bottom
                        intArrayOf(count2, count5)//right
                    )
                }
            }

            for (r in grid.indices) {
                for (c in grid[r].indices) {
                    val sum1 = sums[r][c]

                    sums.getOrNull(r - 1)?.getOrNull(c)?.get(0)?.also { (c2, c5) ->
                        sum1[0][0] += c2
                        sum1[0][1] += c5
                    }
                    sums.getOrNull(r)?.getOrNull(c - 1)?.get(1)?.also { (c2, c5) ->
                        sum1[1][0] += c2
                        sum1[1][1] += c5
                    }

                    val sum2 = sums[grid.lastIndex - r][grid[0].lastIndex - c]
                    sums.getOrNull(grid.lastIndex - r + 1)?.getOrNull(grid[0].lastIndex - c)?.get(2)?.also { (c2, c5) ->
                        sum2[2][0] += c2
                        sum2[2][1] += c5
                    }
                    sums.getOrNull(grid.lastIndex - r)?.getOrNull(grid[0].lastIndex - c + 1)?.get(3)?.also { (c2, c5) ->
                        sum2[3][0] += c2
                        sum2[3][1] += c5
                    }
                }
            }

            var result = 0

            sums.forEachIndexed { r, row ->
                row.forEachIndexed { c, sum ->
                    var t = grid[r][c]
                    var count2 = 0
                    var count5 = 0

                    while (t % 2 == 0) {
                        count2++
                        t /= 2
                    }


                    while (t % 5 == 0) {
                        count5++
                        t /= 5
                    }

                    result =
                        result.coerceAtLeast((sum[0][0] + sum[1][0] - count2).coerceAtMost(sum[0][1] + sum[1][1] - count5))
                    result =
                        result.coerceAtLeast((sum[1][0] + sum[2][0] - count2).coerceAtMost(sum[1][1] + sum[2][1] - count5))
                    result =
                        result.coerceAtLeast((sum[2][0] + sum[3][0] - count2).coerceAtMost(sum[2][1] + sum[3][1] - count5))
                    result =
                        result.coerceAtLeast((sum[3][0] + sum[0][0] - count2).coerceAtMost(sum[3][1] + sum[0][1] - count5))
                }

            }

            return result
        }
    }

    expect {
        Solution().maxTrailingZeros(
            arrayOf()
        )
    }
}