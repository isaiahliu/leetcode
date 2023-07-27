package p14xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numPoints(darts: Array<IntArray>, r: Int): Int {
            if (darts.size == 1) {
                return 1
            }

            val centers = hashSetOf<Pair<Double, Double>>()

            for (i in darts.indices) {
                val (x1, y1) = darts[i]

                for (j in i + 1 until darts.size) {
                    val (x2, y2) = darts[j]
                    val squaredDistance = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)
                    if (squaredDistance > 4 * r * r) {
                        continue
                    }

                    if (squaredDistance == 4 * r * r) {
                        centers.add(((x1 + x2) / 2.0) to ((y1 + y2) / 2.0))
                    } else {
                        val distance = sqrt(squaredDistance.toDouble())
                        val remainDistance = sqrt(r * r - squaredDistance / 4.0)
                        val xMid = (x1 + x2) / 2.0
                        val yMid = (y1 + y2) / 2.0
                        val xDelta = (x2 - x1) / distance
                        val yDelta = (y2 - y1) / distance
                        centers.add((xMid + remainDistance * yDelta) to (yMid - remainDistance * xDelta))
                        centers.add((xMid - remainDistance * yDelta) to (yMid + remainDistance * xDelta))
                    }
                }
            }

            return centers.map { (rx, ry) ->
                darts.count { (x, y) ->
                    (x - rx) * (x - rx) + (y - ry) * (y - ry) - r * r <= 1e-9
                }
            }.maxOrNull() ?: 1
        }
    }

    measureTimeMillis {
        Solution().numPoints(
            arrayOf(), 5
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

