package p31xx

import util.expect

fun main() {
    class Solution {
        fun minRectanglesToCoverPoints(points: Array<IntArray>, w: Int): Int {
            var result = 0

            var left = -w - 1

            points.map { it[0] }.toSortedSet().forEach {
                if (it - left > w) {
                    left = it
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().minRectanglesToCoverPoints(
            arrayOf(), 1
        )
    }
}
