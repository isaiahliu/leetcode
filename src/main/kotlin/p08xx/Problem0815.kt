package p08xx

import util.input
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
            if (source == target) {
                return 0
            }

            val stops = hashSetOf(source)

            var result = 1

            val routeSet = routes.map { it.toSet() }.toMutableSet()

            var found = true
            while (found && routeSet.isNotEmpty()) {
                found = false
                val current = stops.toSet()
                routeSet.toSet().forEach { r ->
                    if (r.any { it in current }) {
                        found = true
                        routeSet.remove(r)
                        stops.addAll(r)
                    }
                }


                if (target in stops) {
                    return result
                }
                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().numBusesToDestination(
            input.map { it.split(",").map { it.toInt() }.toIntArray() }.toTypedArray(), 0, 90000
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}