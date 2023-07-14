package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMaximumGold(grid: Array<IntArray>): Int {
            val cache = hashMapOf<Pair<String, Pair<Int, Int>>, Int>()
            fun dfs(head: Pair<Int, Int>, status: List<Int>): Int {
                val cacheKey = status.joinToString(",") to head

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0

                arrayOf(
                    head.first - 1 to head.second,
                    head.first + 1 to head.second,
                    head.first to head.second - 1,
                    head.first to head.second + 1
                ).filter { (r, c) ->
                    grid.getOrNull(r)?.getOrNull(c)?.takeIf { it > 0 } != null && (status[r] and (1 shl c)) == 0
                }.forEach { (r, c) ->
                    val newStatus = status.toMutableList()
                    newStatus[r] = newStatus[r] + (1 shl c)
                    result = result.coerceAtLeast(
                        dfs(r to c, newStatus) + grid[r][c]
                    )
                }

                cache[cacheKey] = result

                return result
            }

            var result = 0

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        result = result.coerceAtLeast(num + dfs(r to c, List(grid.size) {
                            if (it == r) {
                                1 shl c
                            } else {
                                0
                            }
                        }))
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getMaximumGold(
            arrayOf(
                intArrayOf(0, 6, 0),
                intArrayOf(5, 8, 7),
                intArrayOf(0, 9, 0)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
