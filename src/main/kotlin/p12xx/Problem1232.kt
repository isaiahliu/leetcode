package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
            val deltaY = coordinates[1][1] - coordinates[0][1]
            val deltaX = coordinates[1][0] - coordinates[0][0]

            for (i in 2 until coordinates.size) {
                val (x, y) = coordinates[i]

                if (deltaY * (x - coordinates[0][0]) != deltaX * (y - coordinates[0][1])) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().checkStraightLine(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

