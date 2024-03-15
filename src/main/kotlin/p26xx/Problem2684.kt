package p26xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun maxMoves(grid: Array<IntArray>): Int {
            val dp = grid.indices.toMutableSet()

            var result = 1

            while (result < grid[0].size && dp.isNotEmpty()) {
                dp.toSet().also { dp.clear() }.forEach {
                    val num = grid[it][result - 1]
                    dp += intArrayOf(it - 1, it, it + 1).filter {
                        grid.getOrNull(it)?.get(result)?.takeIf { it > num } != null
                    }
                }

                result += dp.size.sign
            }

            return result - 1
        }
    }

    expect {
        Solution().maxMoves(
            arrayOf(
                intArrayOf(187, 167, 209, 251, 152, 236, 263, 128, 135),
                intArrayOf(267, 249, 251, 285, 73, 204, 70, 207, 74),
                intArrayOf(189, 159, 235, 66, 84, 89, 153, 111, 189),
                intArrayOf(120, 81, 210, 7, 2, 231, 92, 128, 218),
                intArrayOf(193, 131, 244, 293, 284, 175, 226, 205, 245)
            )
        )
    }
}