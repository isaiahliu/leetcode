package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getLengthOfOptimalCompression(s: String, k: Int): Int {
            fun Int.serializeSize(): Int {
                return when (this) {
                    0 -> 0
                    1 -> 1
                    else -> this.toString().length + 1
                }
            }

            val cache = hashMapOf<Pair<Pair<Char, Int>, Pair<Int, Int>>, Int>()
            fun dfs(lastChar: Char, lastCharCount: Int, index: Int, remain: Int): Int {
                if (index == s.length) {
                    return lastCharCount.serializeSize()
                }

                val cacheKey = (lastChar to lastCharCount) to (index to remain)
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE

                result = result.coerceAtMost(
                    if (s[index] == lastChar) {
                        dfs(
                            lastChar, lastCharCount + 1, index + 1, remain.coerceAtMost(s.length - index)
                        )
                    } else {
                        lastCharCount.serializeSize() + dfs(
                            s[index], 1, index + 1, remain.coerceAtMost(s.length - index)
                        )
                    }
                )

                if (remain > 0) {
                    result = result.coerceAtMost(dfs(lastChar, lastCharCount, index + 1, remain - 1))
                }

                cache[cacheKey] = result
                return result
            }

            return dfs('-', 0, 0, k)
        }
    }

    measureTimeMillis {
        Solution().getLengthOfOptimalCompression(
            "abc", 2
        ).also { println(it) }
    }
}

