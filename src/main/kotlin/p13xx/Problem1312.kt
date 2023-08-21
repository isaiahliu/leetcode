package p13xx

import util.expect

fun main() {
    class Solution {
        fun minInsertions(s: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(start: Int, end: Int): Int {
                if (start >= end) {
                    return 0
                }

                val cacheKey = start to end

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val result = if (s[start] == s[end]) {
                    dfs(start + 1, end - 1)
                } else {
                    dfs(start + 1, end).coerceAtMost(dfs(start, end - 1)) + 1
                }

                cache[cacheKey] = result
                return result
            }

            return dfs(0, s.lastIndex)
        }
    }

    expect {
        Solution().minInsertions(
            "mbadm"
        )
    }
}

