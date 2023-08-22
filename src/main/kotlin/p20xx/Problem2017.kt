package p20xx

import util.expect

fun main() {
    class Solution {
        fun gridGame(grid: Array<IntArray>): Long {
            var result = Long.MAX_VALUE

            var sum1 = grid[0].fold(0L) { a, b -> a + b }
            var sum2 = 0L

            repeat(grid[0].size) {
                sum1 -= grid[0][it]

                result = result.coerceAtMost(sum1.coerceAtLeast(sum2))

                sum2 += grid[1][it]
            }

            return result
        }
    }

    expect {
        Solution().gridGame(
            arrayOf(
                intArrayOf(2, 5, 4),
                intArrayOf(1, 5, 1),
            )
        )
    }
}