package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countGoodRectangles(rectangles: Array<IntArray>): Int {
            var result = 0
            var max = 0

            rectangles.forEach { (w, h) ->
                w.coerceAtMost(h).also {
                    if (it > max) {
                        max = it
                        result = 0
                    }

                    if (it == max) {
                        result++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countGoodRectangles(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
