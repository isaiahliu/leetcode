package p14xx

import util.expect

fun main() {
    class Solution {
        fun minCost(houses: IntArray, cost: Array<IntArray>, m: Int, n: Int, target: Int): Int {
            //houseIndex to precolor -- diffArea to totalCost
            val cache = hashMapOf<Pair<Int, Int>, Map<Int, Int>>()
            fun dfs(houseIndex: Int, precolor: Int): Map<Int, Int> {
                if (houseIndex == m) {
                    return mapOf(0 to 0)
                }

                val cacheKey = houseIndex to precolor

                if (cacheKey in cache) {
                    return cache[cacheKey].orEmpty()
                }

                val result = hashMapOf<Int, Int>()

                fun process(colorIndex: Int, paintCost: Int) {
                    val baseDiff = if (colorIndex == precolor) 0 else 1

                    dfs(houseIndex + 1, colorIndex).forEach { (d, t) ->
                        val diff = d + baseDiff
                        val total = t + paintCost

                        if (diff <= target) {
                            result[diff] = result[diff]?.takeIf { it < total } ?: total
                        }
                    }
                }
                if (houses[houseIndex] > 0) {
                    process(houses[houseIndex] - 1, 0)
                } else {
                    cost[houseIndex].forEachIndexed(::process)
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(0, -1)[target] ?: -1
        }
    }

    expect {
        Solution().minCost(
            intArrayOf(0, 0, 0, 0, 0), arrayOf(
                intArrayOf(1, 10),
                intArrayOf(10, 1),
                intArrayOf(10, 1),
                intArrayOf(1, 10),
                intArrayOf(5, 1),
            ), 5, 2, 3
        )

    }
}

