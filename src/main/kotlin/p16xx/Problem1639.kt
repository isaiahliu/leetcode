package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numWays(words: Array<String>, target: String): Int {
            val counts = Array(words[0].length) {
                LongArray(26)
            }

            words.forEach {
                it.forEachIndexed { index, c ->
                    counts[index][c - 'a']++
                }
            }

            val m = 1000000007
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(wordIndex: Int, charIndex: Int): Int {
                if (charIndex == target.length) {
                    return 1
                }
                if (wordIndex == counts.size) {
                    return 0
                }

                val cacheKey = wordIndex to charIndex
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val chars = counts[wordIndex]

                var result = dfs(wordIndex + 1, charIndex).toLong()

                if (chars[target[charIndex] - 'a'] > 0) {
                    result += chars[target[charIndex] - 'a'] * dfs(wordIndex + 1, charIndex + 1)
                    result %= m
                }

                cache[cacheKey] = result.toInt()
                return result.toInt()
            }

            return dfs(0, 0)
        }
    }

    measureTimeMillis {
        Solution().numWays(
            arrayOf("abba", "baab"), "bab"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}