package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDistance(houses: IntArray, k: Int): Int {
            if (k >= houses.size) {
                return 0
            }

            houses.sort()

            var sum = 0
            val sums = IntArray(houses.size) {
                sum += houses[it]
                sum
            }

            fun distance(from: Int, to: Int = houses.lastIndex): Int {
                return when (from) {
                    to -> {
                        0
                    }

                    to - 1 -> {
                        houses[to] - houses[from]
                    }

                    else -> {
                        val mid = (from + to) / 2

                        val midNum = houses[mid]

                        val leftPart = midNum * (mid - from) - (sums[mid - 1] - (sums.getOrNull(from - 1) ?: 0))
                        val rightPart = sums[to] - sums[mid - 1] - midNum * (to - mid + 1)

                        leftPart + rightPart
                    }
                }
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(index: Int, remain: Int): Int {
                val cacheKey = index to remain

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE
                if (remain == 1) {
                    result = distance(index)
                } else {
                    for (i in index..houses.size - remain) {
                        result = result.coerceAtMost(distance(index, i) + dfs(i + 1, remain - 1))
                    }
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(0, k)
        }
    }

    measureTimeMillis {
        Solution().minDistance(
            intArrayOf(1, 4), 1
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

