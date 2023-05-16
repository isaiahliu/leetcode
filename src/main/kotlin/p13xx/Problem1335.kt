package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
            if (jobDifficulty.size < d) {
                return -1
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(leftIndex: Int, remainingDays: Int): Int {
                val cacheKey = leftIndex to remainingDays

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE
                var left = Int.MIN_VALUE
                if (remainingDays == 1) {
                    for (i in leftIndex until jobDifficulty.size) {
                        left = left.coerceAtLeast(jobDifficulty[i])
                    }

                    result = left
                } else {
                    for (i in leftIndex..jobDifficulty.lastIndex - remainingDays + 1) {
                        left = left.coerceAtLeast(jobDifficulty[i])

                        result = result.coerceAtMost(
                            left + dfs(
                                i + 1, remainingDays - 1
                            )
                        )
                    }
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(0, d)
        }
    }

    measureTimeMillis {
        Solution().minDifficulty(
            intArrayOf(6, 5, 4, 3, 2, 1), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}