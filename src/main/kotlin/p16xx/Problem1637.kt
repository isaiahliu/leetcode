package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxWidthOfVerticalArea(points: Array<IntArray>): Int {
            var result = 0
            points.map { it[0] }.toSortedSet().reduce { a, b ->
                result = result.coerceAtLeast(b - a)
                b
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxWidthOfVerticalArea(
            arrayOf()

        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}