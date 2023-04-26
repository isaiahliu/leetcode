package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numPermsDISequence(s: String): Int {
            val m = 1000000007

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Long>()

            fun dfs(lower: Int, higher: Int, index: Int): Long {
                if (index == s.length) {
                    return 1L
                }

                val cacheKey = lower to higher to index
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0L
                }

                var result = 0L

                when (s[index]) {
                    'D' -> {
                        for (i in 0 until lower) {
                            result += dfs(i, higher + lower - i - 1, index + 1)
                            result %= m
                        }
                    }

                    'I' -> {
                        for (i in 0 until higher) {
                            result += dfs(lower + higher - i - 1, i, index + 1)
                            result %= m
                        }
                    }
                }

                cache[cacheKey] = result
                return result
            }

            var result = 0L

            repeat(s.length + 1) {
                result += dfs(it, s.length - it, 0)
                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().numPermsDISequence(
            "DIDIDIDIDIDIDIDIDIDIDIDD"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}