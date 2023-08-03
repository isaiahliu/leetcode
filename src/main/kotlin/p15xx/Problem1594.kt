package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProductPath(grid: Array<IntArray>): Int {
            val cache = hashMapOf<Pair<Int, Int>, Pair<Long, Long>?>()
            fun dfs(rowIndex: Int, columnIndex: Int): Pair<Long, Long>? {
                if (rowIndex >= grid.size || columnIndex >= grid[0].size) {
                    return null
                }

                val num = grid[rowIndex][columnIndex].toLong()
                if (rowIndex == grid.lastIndex && columnIndex == grid[0].lastIndex) {
                    return num.let { it to it }
                }

                val cacheKey = rowIndex to columnIndex
                if (cacheKey in cache) {
                    return cache[cacheKey]
                }

                var minResult = Long.MAX_VALUE
                var maxResult = Long.MIN_VALUE

                dfs(rowIndex + 1, columnIndex)?.also { (min, max) ->
                    minResult = minResult.coerceAtMost(min * num).coerceAtMost(max * num)
                    maxResult = maxResult.coerceAtLeast(min * num).coerceAtLeast(max * num)
                }

                dfs(rowIndex, columnIndex + 1)?.also { (min, max) ->
                    minResult = minResult.coerceAtMost(min * num).coerceAtMost(max * num)
                    maxResult = maxResult.coerceAtLeast(min * num).coerceAtLeast(max * num)
                }

                return (minResult to maxResult).also {
                    cache[cacheKey] = it
                }
            }

            return dfs(0, 0)?.second?.takeIf {
                it >= 0
            }?.let {
                it % 1000000007
            }?.toInt() ?: -1
        }
    }

    measureTimeMillis {
        Solution().maxProductPath(
            arrayOf(
                intArrayOf(1, -2, 1), intArrayOf(1, -2, 1), intArrayOf(3, -4, 1)
            )
        ).also { println("${it} should be ${it}") }
    }
}

