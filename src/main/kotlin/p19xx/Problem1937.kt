package p19xx

import util.expect

fun main() {
    class Solution {
        fun maxPoints(points: Array<IntArray>): Long {
            var dp = LongArray(points[0].size) {
                points[0][it].toLong()
            }

            for (i in 1 until points.size) {
                val newDp = LongArray(dp.size)

                var max = 0L
                for (index in dp.indices) {
                    max--
                    max = max.coerceAtLeast(dp[index])

                    newDp[index] = max
                }

                max = 0L
                for (index in dp.lastIndex downTo 0) {
                    max--
                    max = max.coerceAtLeast(dp[index])

                    newDp[index] = newDp[index].coerceAtLeast(max) + points[i][index]
                }

                dp = newDp
            }

            return dp.max()
        }
    }

    expect {
        Solution().maxPoints(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(1, 5, 1),
                intArrayOf(3, 1, 1),
            )
        )
    }
}