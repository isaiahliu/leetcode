package p32xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(m: Int, n: Int, horizontalCut: IntArray, verticalCut: IntArray): Int {
            val cache = Array(m) {
                Array(m) { mSize ->
                    Array(n) {
                        IntArray(n) { nSize ->
                            0 - (mSize + nSize)
                        }
                    }
                }
            }

            fun dfs(hRange: IntRange, vRange: IntRange): Int {
                val hSize = hRange.last - hRange.first + 1
                val vSize = vRange.last - vRange.first + 1

                if (cache[hRange.first][hSize][vRange.first][vSize] < 0) {
                    var min = Int.MAX_VALUE

                    hRange.forEach {
                        min = minOf(min, horizontalCut[it] + dfs(hRange.first until it, vRange) + dfs(it + 1..hRange.last, vRange))
                    }

                    vRange.forEach {
                        min = minOf(min, verticalCut[it] + dfs(hRange, vRange.first until it) + dfs(hRange, it + 1..vRange.last))
                    }

                    cache[hRange.first][hSize][vRange.first][vSize] = min
                }

                return cache[hRange.first][hSize][vRange.first][vSize]
            }

            return dfs(horizontalCut.indices, verticalCut.indices)
        }
    }

    expect {
        Solution().minimumCost(
            3, 2,
            intArrayOf(1, 3),
            intArrayOf(5),
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
