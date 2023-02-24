package p02xx

fun main() {
    class Solution {
        fun maximalSquare(matrix: Array<CharArray>): Int {
            var max = 0
            val dp = Array(matrix.size) { r ->
                Array(matrix[r].size) { c ->
                    (matrix[r][c] - '0').let {
                        max = max.coerceAtLeast(it)

                        intArrayOf(it, it, it)
                    }
                }
            }


            for (r in 1 until dp.size) {
                for (c in 1 until dp[r].size) {
                    if (dp[r][c][0] > 0) {
                        dp[r][c][0] += dp[r][c - 1][0]
                        dp[r][c][1] += dp[r - 1][c][1]

                        dp[r][c][2] += dp[r - 1][c - 1][2].coerceAtMost(dp[r][c][0] - 1).coerceAtMost(dp[r][c][1] - 1)
                        max = max.coerceAtLeast(dp[r][c][2])
                    }
                }
            }

            return max * max
        }
    }

    println(
        Solution().maximalSquare(
            arrayOf(
                charArrayOf('0', '0', '0', '1'),
                charArrayOf('1', '1', '0', '1'),
                charArrayOf('1', '1', '1', '1'),
                charArrayOf('0', '1', '1', '1'),
                charArrayOf('0', '1', '1', '1'),
            )
        )
    )
}

