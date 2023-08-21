package p09xx

import kotlin.math.sqrt
import util.expect


fun main() {
    class Solution {
        fun minAreaFreeRect(points: Array<IntArray>): Double {
            val set = points.map { it[0] to it[1] }.toSet()

            var result = Double.MAX_VALUE
            for (i in points.indices) {
                val (x1, y1) = points[i]

                for (j in i + 1 until points.size) {
                    val (x2, y2) = points[j]

                    for (k in j + 1 until points.size) {
                        val (x3, y3) = points[k]
                        val p4 = x2 + x3 - x1 to y2 + y3 - y1

                        if (p4 in set) {
                            val dot: Int = (x2 - x1) * (x3 - x1) +
                                    (y2 - y1) * (y3 - y1)
                            if (dot == 0) {
                                result =
                                    result.coerceAtMost(sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)).toDouble() * ((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1))))
                            }
                        }
                    }
                }
            }

            return if (result < Double.MAX_VALUE) result else 0.0
        }
    }

    expect {
        Solution().minAreaFreeRect(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 1),
                intArrayOf(1, 0),
                intArrayOf(0, 1),
            )
        )
    }
}
