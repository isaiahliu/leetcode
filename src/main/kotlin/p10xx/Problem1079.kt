package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTilePossibilities(tiles: String): Int {
            val sortedTiles = String(tiles.toCharArray().also { it.sort() })

            fun dfs(route: Set<Int>): Int {
                var result = 1
                sortedTiles.forEachIndexed { index, c ->
                    if (index !in route) {
                        if (sortedTiles.getOrNull(index - 1) == c) {
                            if (index - 1 in route) {
                                result += dfs(route + index)
                            }
                        } else {
                            result += dfs(route + index)
                        }
                    }
                }

                return result
            }

            return dfs(emptySet()) - 1
        }
    }

    measureTimeMillis {
        Solution().numTilePossibilities(
            "DB"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}