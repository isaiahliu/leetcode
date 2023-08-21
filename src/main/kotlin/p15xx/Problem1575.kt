package p15xx

import util.expect

fun main() {
    class Solution {
        fun countRoutes(locations: IntArray, start: Int, finish: Int, fuel: Int): Int {
            val m = 1000000007
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(pos: Int, remain: Int): Int {
                if (remain < 0) {
                    return 0
                }

                val cacheKey = pos to remain
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0L

                if (pos == finish) {
                    result++
                }

                val location = locations[pos]
                locations.forEachIndexed { index, i ->
                    if (index != pos) {
                        val cost = if (location > i) {
                            location - i
                        } else {
                            i - location
                        }

                        result += dfs(index, remain - cost)
                        result %= m
                    }
                }

                cache[cacheKey] = result.toInt()
                return result.toInt()
            }

            return dfs(start, fuel)
        }
    }

    expect {
        Solution().countRoutes(
            intArrayOf(2, 3, 6, 8, 4), 1, 3, 5
        )
    }
}

