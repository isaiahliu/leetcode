package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
            var count0 = 0
            var count1 = 0
            val counts = strs.map {
                it.count { it == '0' }.also { count0 += it } to it.count { it == '1' }.also { count1 += it }
            }.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

            if (count0 <= m && count1 <= n) {
                return strs.size
            }

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Int?>()

            fun dfs(index: Int, zeroCount: Int, oneCount: Int): Int? {
                if (zeroCount > m || oneCount > n) {
                    return null
                }

                if (index >= counts.size) {
                    return 0
                }

                val cacheKey = index to zeroCount to oneCount
                if (cacheKey in cache) {
                    return cache[cacheKey]
                }

                val (zero, one) = counts[index]

                var result: Int? = null

                dfs(index + 1, zeroCount, oneCount)?.also {
                    result = it
                }

                dfs(index + 1, zeroCount + zero, oneCount + one)?.also {
                    result = (it + 1).coerceAtLeast(result ?: 0)
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(0, 0, 0) ?: 0
        }
    }

    measureTimeMillis {
        Solution().findMaxForm(
            arrayOf("10", "0001", "111001", "1", "0"), 5, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}