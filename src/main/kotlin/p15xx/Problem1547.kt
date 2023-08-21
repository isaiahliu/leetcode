package p15xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minCost(n: Int, cuts: IntArray): Int {
            val cutSet = TreeSet<Int>()
            cuts.forEach { cutSet.add(it) }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(from: Int, to: Int): Int {
                val cacheKey = from to to

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = Int.MAX_VALUE
                cutSet.subSet(from, false, to, false).takeIf { it.isNotEmpty() }?.also {
                    it.forEach { c ->
                        result = result.coerceAtMost(dfs(from, c) + dfs(c, to))
                    }

                    result += to - from
                } ?: run {
                    result = 0
                }

                cache[cacheKey] = result
                return result
            }

            return dfs(0, n)
        }
    }

    expect {
        Solution().minCost(
            5, intArrayOf()
        )
    }
}

