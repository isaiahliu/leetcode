package p10xx

import util.expect

fun main() {
    class Solution {
        fun minScoreTriangulation(values: IntArray): Int {
            val cache = hashMapOf<Int, Int>()
            val n = values.size

            fun dp(i: Int, j: Int): Int {
                val key = i * n + j
                return when {
                    i + 2 > j -> {
                        0
                    }

                    i + 2 == j -> {
                        values[i] * values[i + 1] * values[j]
                    }

                    key in cache -> {
                        cache[key] ?: 0
                    }

                    else -> {
                        var result = Int.MAX_VALUE
                        for (k in i + 1 until j) {
                            result = result.coerceAtMost(values[i] * values[k] * values[j] + dp(i, k) + dp(k, j))
                        }
                        cache[key] = result
                        result
                    }
                }
            }

            return dp(0, n - 1)
        }
    }

    expect {
        Solution().minScoreTriangulation(
            intArrayOf(1, 2, 3, 4)
        )
    }
}
