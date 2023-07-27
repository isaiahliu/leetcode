package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun cherryPickup(grid: Array<IntArray>): Int {
            val colRange = grid[0].indices

            val cache = Array(grid.size) {
                hashMapOf<Pair<Int, Int>, Int>()
            }

            fun dfs(rowIndex: Int, left: Int, right: Int): Int {
                if (rowIndex == grid.size) {
                    return 0
                }

                val cacheKey = left to right
                if (cacheKey in cache[rowIndex]) {
                    return cache[rowIndex][cacheKey] ?: 0
                }

                var result = 0

                arrayOf(
                    -1 to -1, -1 to 0, -1 to 1,
                    0 to -1, 0 to 0, 0 to 1,
                    1 to -1, 1 to 0, 1 to 1,
                ).forEach { (dl, dr) ->
                    val newLeft = left + dl
                    val newRight = right + dr

                    if (newLeft < newRight && newLeft in colRange && newRight in colRange) {
                        result = result.coerceAtLeast(dfs(rowIndex + 1, newLeft, newRight))
                    }
                }

                result += grid[rowIndex][left] + grid[rowIndex][right]

                cache[rowIndex][cacheKey] = result

                return result
            }

            return dfs(0, 0, colRange.last)
        }
    }

    measureTimeMillis {
        Solution().cherryPickup(
            arrayOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

