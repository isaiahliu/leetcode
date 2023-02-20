package p01xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().minimumTotal(emptyList()).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

