package p04xx

import util.expect

fun main() {
    class Solution {
        fun findMinArrowShots(points: Array<IntArray>): Int {
            points.sortBy { it[1] }

            var lastEnd = points[0][0].toLong() - 1
            var result = 0
            points.forEach { (start, end) ->
                if (start > lastEnd) {
                    result++
                    lastEnd = end.toLong()
                }
            }

            return result
        }
    }

    expect {
        Solution().findMinArrowShots(arrayOf())
    }
}