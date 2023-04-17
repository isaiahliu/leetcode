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

            while (routeSet.isNotEmpty()) {
                val current = stops.toSet()

                val newRoutes = routeSet.filter { r ->
                    r.any { it in current }
                }.toSet().onEach {
                    routeSet.remove(it)
                    stops.addAll(it)
                }

                if (newRoutes.isEmpty()) {
                    return -1
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