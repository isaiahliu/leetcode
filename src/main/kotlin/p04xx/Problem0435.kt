package p04xx

import util.expect

fun main() {
    class Solution {
        fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
            intervals.sortBy { it[0] }

            var result = 0
            var right = intervals[0][0] - 1
            intervals.forEach { (l, r) ->
                if (l < right) {
                    result++
                    right = right.coerceAtMost(r)
                } else {
                    right = r
                }

            }

            return result
        }
    }

    expect {
        Solution().eraseOverlapIntervals(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 3),
            )
        )
    }
}


