package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distanceBetweenBusStops(distance: IntArray, start: Int, destination: Int): Int {
            val total = distance.sum()

            val d = (start.coerceAtMost(destination) until start.coerceAtLeast(destination)).map {
                distance[it]
            }.sum()

            return d.coerceAtMost(total - d)
        }
    }

    measureTimeMillis {
        println(
            Solution().distanceBetweenBusStops(
                intArrayOf(1, -2, 0, 3), 1, 2
            )
        )
    }.also { println("Time cost: ${it}ms") }
}

