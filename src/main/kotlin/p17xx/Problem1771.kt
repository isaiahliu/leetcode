package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestPalindrome(word1: String, word2: String): Int {
            val word = word1 + word2

            val leftIndices = IntArray(26) { -1 }
            val rightIndices = IntArray(26) { -1 }

            for (index in word1.lastIndex downTo 0) {
                leftIndices[word1[index] - 'a'] = index
            }
            for (index in word2.indices) {
                rightIndices[word2[index] - 'a'] = word2.lastIndex - index
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(left: Int, right: Int): Int {
                val cacheKey = left to right
                return when {
                    left > right -> {
                        0
                    }

                    left == right -> {
                        1
                    }

                    word[left] == word[right] -> {
                        dfs(left + 1, right - 1) + 2
                    }

                    cacheKey in cache -> {
                        cache[cacheKey] ?: 0
                    }

                    else -> {
                        val result = dfs(left + 1, right).coerceAtLeast(dfs(left, right - 1))

                        cache[cacheKey] = result
                        return result
                    }
                }
            }

            var result = 0
            repeat(26) {
                if (leftIndices[it] >= 0 && rightIndices[it] >= 0) {
                    result = result.coerceAtLeast(2 + dfs(leftIndices[it] + 1, word.lastIndex - rightIndices[it] - 1))
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestPalindrome(
            "cacb", "cbba"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
