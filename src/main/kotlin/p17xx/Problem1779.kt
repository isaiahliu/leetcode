package p17xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun nearestValidPoint(x: Int, y: Int, points: Array<IntArray>): Int {
            return points.indices.filter { points[it][0] == x || points[it][1] == y }.minWithOrNull(compareBy<Int> {
                (points[it][0] - x).absoluteValue + (points[it][1] - y).absoluteValue
            }.thenBy { it }) ?: -1
        }
    }

    expect {
        Solution().nearestValidPoint(
            1, 2, arrayOf()
        )
    }
}
