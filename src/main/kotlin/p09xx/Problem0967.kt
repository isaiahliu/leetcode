package p09xx

import util.expect

fun main() {
    class Solution {
        fun numsSameConsecDiff(n: Int, k: Int): IntArray {
            if (k == 0) {
                val base = "1".repeat(n).toInt()

                return IntArray(9) {
                    base * (it + 1)
                }
            }
            val cache = hashMapOf<Pair<Int, Int>, List<String>>()

            fun dfs(start: Int, length: Int): List<String> {
                if (length == 1) {
                    return listOf("$start")
                }

                val cacheKey = start to length
                if (cacheKey in cache) {
                    return cache[cacheKey].orEmpty()
                }

                val result = arrayListOf<String>()

                (start + k).takeIf { it < 10 }?.let { dfs(it, length - 1) }?.map {
                    "${start}$it"
                }?.also {
                    result.addAll(it)
                }

                (start - k).takeIf { it >= 0 }?.let { dfs(it, length - 1) }?.map {
                    "${start}$it"
                }?.also {
                    result.addAll(it)
                }

                cache[cacheKey] = result

                return result
            }

            return (1..9).map { num ->
                dfs(num, n)
            }.flatten().map { it.toInt() }.toIntArray()
        }
    }

    expect {
        Solution().numsSameConsecDiff(
            2, 1
        ).toList()
    }
}
