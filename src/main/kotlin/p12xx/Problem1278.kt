package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun palindromePartition(s: String, k: Int): Int {
            fun calculate(start: Int, end: Int): Int {
                var l = start
                var r = end

                var result = 0
                while (l < r) {
                    if (s[l++] != s[r--]) {
                        result++
                    }
                }

                return result
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(index: Int, remaining: Int): Int {
                if (remaining == 1) {
                    return calculate(index, s.lastIndex)
                }

                val cacheKey = index to remaining

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE

                for (end in index..s.length - remaining) {
                    result = result.coerceAtMost(calculate(index, end) + dfs(end + 1, remaining - 1))
                }

                cache[cacheKey] = result
                return result
            }

            return dfs(0, k)
        }
    }

    measureTimeMillis {
        Solution().palindromePartition(
            "aabbc", 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
