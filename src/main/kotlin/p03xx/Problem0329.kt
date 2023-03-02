package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestIncreasingPath(matrix: Array<IntArray>): Int {
            val nums = matrix.mapIndexed { r, row -> row.mapIndexed { c, value -> (r to c) to value } }.flatten()
                .sortedBy { it.second }

            val dp = hashMapOf<Pair<Int, Int>, Int>()

            var result = 0
            nums.forEach { (pair, value) ->
                val (r, c) = pair

                val length = (arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).mapNotNull { t ->
                    matrix.getOrNull(t.first)?.getOrNull(t.second)?.takeIf { value > it }?.let {
                        dp[t]
                    }
                }.maxOrNull() ?: 0) + 1

                dp[pair] = length
                result = result.coerceAtLeast(length)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestIncreasingPath(
            arrayOf(
                intArrayOf(9, 9, 4),
                intArrayOf(6, 6, 8),
                intArrayOf(2, 1, 1),
            )
        ).also { println(it) }
    }
}

