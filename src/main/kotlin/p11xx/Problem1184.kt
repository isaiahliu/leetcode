package p11xx

import util.expect

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

    expect {
        Solution().distanceBetweenBusStops(
            intArrayOf(1, -2, 0, 3), 1, 2
        )
    }
}

