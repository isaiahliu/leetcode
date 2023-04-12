package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestDecomposition(text: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun find(startIndex: Int, endIndex: Int): Int {
                if (startIndex > endIndex) {
                    return 0
                }

                val cacheKey = startIndex to endIndex
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var s = startIndex
                var r = endIndex

                val left = StringBuilder()
                val right = StringBuilder()

                var max = 1

                while (s < r) {
                    left.append(text[s++])
                    right.insert(0, text[r--])

                    if (left.toString() == right.toString()) {
                        max = max.coerceAtLeast(2 + find(s, r))
                    }
                }

                cache[cacheKey] = max
                return max
            }

            return find(0, text.lastIndex)
        }
    }

    measureTimeMillis {
        Solution().longestDecomposition(
            "ghiabcdefhelloadamhelloabcdefghi"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}