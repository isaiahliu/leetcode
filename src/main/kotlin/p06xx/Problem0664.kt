package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun strangePrinter(s: String): Int {
            val str = StringBuilder()
            s.forEachIndexed { index, c ->
                if (c != s.getOrNull(index - 1)) {
                    str.append(c)
                }
            }

            val cache = hashMapOf<Pair<Pair<Int, Int>, Char>, Int>()

            fun find(startIndex: Int, endIndex: Int, char: Char): Int {
                if (startIndex > endIndex) {
                    return 0
                }

                if (startIndex == endIndex) {
                    return if (str[startIndex] == char) 0 else 1
                }

                val cacheKey = startIndex to endIndex to char
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE
                when (char) {
                    str[startIndex] -> {
                        result = result.coerceAtMost(find(startIndex + 1, endIndex, char))
                    }

                    str[endIndex] -> {
                        result = result.coerceAtMost(find(startIndex, endIndex - 1, char))
                    }

                    else -> {
                        result = result.coerceAtMost(find(startIndex + 1, endIndex, str[startIndex]) + 1)
                        result = result.coerceAtMost(find(startIndex, endIndex - 1, str[endIndex]) + 1)

                        for (i in startIndex..endIndex) {
                            if (str[i] == char) {
                                result = result.coerceAtMost(
                                    find(startIndex, i - 1, char) + find(
                                        i + 1, endIndex, char
                                    )
                                )
                            }
                        }
                    }
                }

                cache[cacheKey] = result
                return result
            }

            return find(0, str.lastIndex, ' ')
        }
    }

    measureTimeMillis {
        Solution().strangePrinter(
            "bdabcbadcddcacadacbbaddbbbacdbacadabcbdbdcd"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}