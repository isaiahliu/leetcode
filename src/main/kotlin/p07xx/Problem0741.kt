package p07xx

import util.expect

fun main() {
    class Solution {
        fun cherryPickup(grid: Array<IntArray>): Int {
            val n = grid.size
            val cache = hashMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int?>()
            fun find(r1: Int, c1: Int, r2: Int, c2: Int): Int? {
                when {
                    r1 < 0 || c1 < 0 || r2 < 0 || c2 < 0 -> {
                        return null
                    }

                    r1 == 0 && c1 == 0 && r2 == 0 && c2 == 0 -> {
                        return grid[0][0]
                    }

                    grid[r1][c1] == -1 || grid[r2][c2] == -1 -> {
                        return null
                    }

                    else -> {
                        val cacheKey = (r1 to c1) to (r2 to c2)
                        if (cacheKey in cache) {
                            return cache[cacheKey]
                        }

                        var result: Int? = arrayOf(
                            find(r1 - 1, c1, r2 - 1, c2),
                            find(r1, c1 - 1, r2, c2 - 1),
                            find(r1 - 1, c1, r2, c2 - 1),
                            find(r1, c1 - 1, r2 - 1, c2)
                        ).filterNotNull().maxOrNull()?.let { it + grid[r1][c1] + grid[r2][c2] }

                        if (r1 == r2 && c1 == c2) {
                            result = result?.let { it - grid[r1][c1] }
                        }

                        cache[cacheKey] = result

                        return result
                    }
                }
            }

            return find(n - 1, n - 1, n - 1, n - 1) ?: 0
        }
    }

    expect {
        Solution().cherryPickup(
            arrayOf()
        )
    }
}