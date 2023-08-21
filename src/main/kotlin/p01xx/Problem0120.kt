package p01xx

import util.expect

fun main() {
    class Solution {
        fun minimumTotal(triangle: List<List<Int>>): Int {
            val dp = triangle.map { it.toIntArray() }

            var min = dp[0][0]

            var previousRow = dp[0]
            dp.drop(1).forEach { row ->
                min = row[0] + previousRow[0]

                row.indices.forEach {
                    row[it] += arrayOf(it - 1, it).mapNotNull { previousRow.getOrNull(it) }.min()

                    min = min.coerceAtMost(row[it])
                }

                previousRow = row
            }

            return min
        }
    }

    expect {
        Solution().minimumTotal(emptyList())
    }
}

