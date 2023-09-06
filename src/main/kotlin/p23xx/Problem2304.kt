package p23xx

import util.expect

fun main() {
    class Solution {
        fun minPathCost(grid: Array<IntArray>, moveCost: Array<IntArray>): Int {
            var dp = IntArray(grid[0].size) {
                grid[0][it]
            }

            for (row in 1 until grid.size) {
                val nextDp = IntArray(dp.size) { Int.MAX_VALUE }

                dp.forEachIndexed { column, cost ->
                    moveCost[grid[row - 1][column]].forEachIndexed { target, c ->
                        nextDp[target] = nextDp[target].coerceAtMost(cost + c)
                    }
                }

                grid[row].forEachIndexed { index, n ->
                    nextDp[index] += n
                }

                dp = nextDp
            }

            return dp.min()
        }
    }

    expect {
        Solution().minPathCost(
            arrayOf(
                intArrayOf(5, 3),
                intArrayOf(4, 0),
                intArrayOf(2, 1),
            ),
            arrayOf(
                intArrayOf(9, 8),
                intArrayOf(1, 5),
                intArrayOf(10, 12),
                intArrayOf(18, 6),
                intArrayOf(2, 4),
                intArrayOf(14, 3)
            )
        )
    }
}