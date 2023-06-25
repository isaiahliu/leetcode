package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkOverlap(radius: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
            if (xCenter in x1..x2 && yCenter in y1..y2) {
                return true
            }

            var cx = xCenter
            var cy = yCenter

            var rx1 = x1
            var rx2 = x2
            var ry1 = y1
            var ry2 = y2

            if (cx > rx1) {
                cx = -xCenter
                rx1 = -x2
                rx2 = -x1
            }

            if (cy > ry1) {
                cy = -yCenter
                ry1 = -y2
                ry2 = -y1
            }

            return when {
                cx <= rx1 && cy <= ry1 -> {
                    (rx1 - cx) * (rx1 - cx) + (ry1 - cy) * (ry1 - cy) <= radius * radius
                }

                cx <= rx1 -> {
                    cx + radius >= rx1 && cy in ry1..ry2
                }

                cy <= ry1 -> {
                    cy + radius >= ry1 && cx in rx1..rx2
                }

                else -> {
                    false
                }
            }
        }
    }

    measureTimeMillis {
        Solution().checkOverlap(
            1,
            0,
            0,
            1,
            -1,
            3,
            1,
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}