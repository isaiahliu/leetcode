package p08xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun largestTriangleArea(points: Array<IntArray>): Double {
            fun calculateSquare(i: Int, j: Int, k: Int): Double {
                val (x1, y1) = points[i]
                val (x2, y2) = points[j]
                val (x3, y3) = points[k]

                return 0.5 * (x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2).absoluteValue
            }

            val n = points.size
            var result = 0.0
            for (i in 0 until n) {
                for (j in i + 1 until n) {
                    for (k in j + 1 until n) {
                        result = result.coerceAtLeast(
                            calculateSquare(i, j, k)
                        )
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().largestTriangleArea(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(0, 2),
                intArrayOf(2, 0),
            )
        )
    }
}