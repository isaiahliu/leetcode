package p36xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minCost(grid: Array<IntArray>, k: Int): Int {
            val jump = hashMapOf<Int, Int>()

            var result = Int.MAX_VALUE
            repeat(k + 1) {
                val dp = Array(grid.size) { IntArray(grid[it].size) { Int.MAX_VALUE } }
                dp[grid.lastIndex][grid[0].lastIndex] = 0

                val newJump = TreeMap<Int, Int>()

                for (r in grid.lastIndex downTo 0) {
                    for (c in grid[r].lastIndex downTo 0) {
                        jump[grid[r][c]]?.also {
                            dp[r][c] = minOf(dp[r][c], it)
                        }

                        dp.getOrNull(r + 1)?.getOrNull(c)?.also {
                            dp[r][c] = minOf(dp[r][c], it + grid[r + 1][c])
                        }

                        dp.getOrNull(r)?.getOrNull(c + 1)?.also {
                            dp[r][c] = minOf(dp[r][c], it + grid[r][c + 1])
                        }

                        newJump[grid[r][c]] = minOf(newJump[grid[r][c]] ?: Int.MAX_VALUE, dp[r][c])
                    }
                }

                jump.clear()
                var min = Int.MAX_VALUE
                newJump.forEach { (key, value) ->
                    min = minOf(min, value)
                    jump[key] = min
                }

                result = minOf(result, dp[0][0])
            }

            return result
        }
    }

    expect {
        Solution().minCost(
            arrayOf(
                intArrayOf(1, 3, 3),
                intArrayOf(2, 5, 4),
                intArrayOf(4, 3, 5)
            ), 2
        )
    }
}
