package p05xx

import util.expect

fun main() {
    class Solution {
        fun minDistance(word1: String, word2: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun maxCommonLength(index1: Int, index2: Int): Int {
                if (index1 == word1.length || index2 == word2.length) {
                    return 0
                }

                val cacheKey = index1 to index2
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val result = if (word1[index1] == word2[index2]) {
                    maxCommonLength(index1 + 1, index2 + 1) + 1
                } else {
                    maxCommonLength(index1, index2 + 1).coerceAtLeast(maxCommonLength(index1 + 1, index2))
                }

                cache[cacheKey] = result
                return result
            }

            return word1.length + word2.length - maxCommonLength(0, 0) * 2
        }
    }

    expect {
        Solution().minDistance(
            "sea", "eat"
        )

    }
}