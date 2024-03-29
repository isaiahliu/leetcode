package p12xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
            var result = 0
            for (i in 1 until points.size) {
                val (x1, y1) = points[i - 1]
                val (x2, y2) = points[i]

                result += (x2 - x1).absoluteValue.coerceAtLeast((y2 - y1).absoluteValue)
            }

            return result
        }
    }

    expect {
        Solution().minTimeToVisitAllPoints(
            arrayOf()
        )
    }
}
