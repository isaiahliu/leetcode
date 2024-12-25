package p32xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(m: Int, n: Int, horizontalCut: IntArray, verticalCut: IntArray): Int {
            val cache = hashMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int>()

            fun dfs(hStart: Int, hEnd: Int, vStart: Int, vEnd: Int): Int {
                val cacheKey = (hStart to hEnd) to (vStart to vEnd)

                return when {
                    hStart > hEnd && vStart > vEnd -> 0
                    cacheKey in cache -> cache[cacheKey] ?: 0
                    else -> {
                        var min = Int.MAX_VALUE

                        (hStart..hEnd).forEach {
                            min = minOf(min, horizontalCut[it] + dfs(hStart, it - 1, vStart, vEnd) + dfs(it + 1, hEnd, vStart, vEnd))
                        }
                        (vStart..vEnd).forEach {
                            min = minOf(min, verticalCut[it] + dfs(hStart, hEnd, vStart, it - 1) + dfs(hStart, hEnd, it + 1, vEnd))
                        }

                        cache[cacheKey] = min
                        min
                    }
                }
            }

            return dfs(0, horizontalCut.lastIndex, 0, verticalCut.lastIndex)
        }
    }

    expect {
        Solution().minimumCost(
            4, 5,
            intArrayOf(0, 3),
            intArrayOf(0, 2),
        )
    }
//    expect {
//        Solution().shortestDistanceAfterQueries(
//            100000,
//            Array(100000 - 1) {
//                intArrayOf(0, it + 1)
//            }
//        )
//    }
}
