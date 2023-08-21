package p15xx

import util.expect

fun main() {
    class Solution {
        fun stoneGameV(stoneValue: IntArray): Int {
            var sum = 0
            val sums = IntArray(stoneValue.size + 1) {
                stoneValue.getOrNull(it - 1)?.also {
                    sum += it
                }

                sum
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(from: Int, to: Int): Int {
                val cacheKey = from to to
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0

                (from + 1 until to).forEach { mid ->
                    val leftSum = sums[mid] - sums[from]
                    val rightSum = sums[to] - sums[mid]

                    result = result.coerceAtLeast(
                        when {
                            leftSum > rightSum -> {
                                rightSum + dfs(mid, to)
                            }

                            leftSum < rightSum -> {
                                leftSum + dfs(from, mid)
                            }

                            else -> {
                                leftSum + dfs(from, mid).coerceAtLeast(dfs(mid, to))
                            }
                        }
                    )
                }

                cache[cacheKey] = result
                return result
            }
            return dfs(0, stoneValue.size)
        }
    }

    expect {
        Solution().stoneGameV(
            intArrayOf(6, 2, 3, 4, 5, 5)
        )
    }
}

