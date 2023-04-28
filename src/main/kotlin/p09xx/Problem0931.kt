package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minFallingPathSum(matrix: Array<IntArray>): Int {
            val dp = Array(matrix.size) { r ->
                IntArray(matrix[r].size) { c ->
                    matrix[r][c]
                }
            }

            for (r in 1 until matrix.size) {
                for (c in matrix[r].indices) {
                    dp[r][c] += arrayOf(r - 1 to c - 1, r - 1 to c, r - 1 to c + 1).mapNotNull {
                        dp[it.first].getOrNull(it.second)
                    }.min()
                }
            }

            return dp[dp.lastIndex].min()
        }
    }

    measureTimeMillis {
        Solution().minFallingPathSum(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}