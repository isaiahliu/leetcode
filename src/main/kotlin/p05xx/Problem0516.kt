package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestPalindromeSubseq(s: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun find(startIndex: Int, endIndex: Int): Int {
                if (startIndex == endIndex) {
                    return 1
                }

                if (startIndex > endIndex) {
                    return 0
                }

                val cacheKey = startIndex to endIndex

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val result: Int

                if (s[startIndex] == s[endIndex]) {
                    result = 2 + find(startIndex + 1, endIndex - 1)
                } else {
                    result = find(startIndex + 1, endIndex).coerceAtLeast(find(startIndex, endIndex - 1))
                }

                cache[cacheKey] = result

                return result
            }

            return find(0, s.lastIndex)
        }
    }

    measureTimeMillis {
        Solution().longestPalindromeSubseq(
            "cbbd"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}