package p30xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPairs(points: Array<IntArray>): Int {
            points.sortWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })

            var result = 0

            for (i in points.indices) {
                val (x1, y1) = points[i]

                var minY = Int.MIN_VALUE

                for (j in i + 1 until points.size) {
                    val (x2, y2) = points[j]

                    if (x2 >= x1 && y2 <= y1 && y2 > minY) {
                        result++

                        minY = y2
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().numberOfPairs(
            arrayOf()
        )
    }
}
