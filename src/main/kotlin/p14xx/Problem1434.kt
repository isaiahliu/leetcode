package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberWays(hats: List<List<Int>>): Int {
            val sorted = hats.sortedBy { it.size }

            var status = 0L
            val usedHats = LongArray(sorted.size)

            for (i in sorted.lastIndex downTo 0) {
                sorted[i].forEach {
                    status = status or (1L shl it)
                }

                usedHats[i] = status
            }

            val m = 1000000007

            val cache = hashMapOf<Pair<Int, Long>, Int>()
            fun dfs(index: Int, status: Long): Int {
                if (index == sorted.size) {
                    return 1
                }

                val washedStatus = status and usedHats[index]

                val cacheKey = index to washedStatus
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0L

                sorted[index].forEach {
                    val p = 1L shl it
                    if (washedStatus and p > 0L) {
                        result += dfs(index + 1, washedStatus - p)
                        result %= m
                    }
                }

                cache[cacheKey] = result.toInt()

                return result.toInt()
            }

            return dfs(0, usedHats[0]).also {
                println()
            }
        }
    }

    measureTimeMillis {
        Solution().numberWays(
            listOf(
                listOf(1, 2, 3, 4, 5, 12, 13, 16, 17, 18, 23, 24, 25, 26, 28, 30, 31, 33, 34, 38, 39, 40), listOf(1)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

