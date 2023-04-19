package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDistToClosest(seats: IntArray): Int {
            var last = -1

            var result = 0
            seats.forEachIndexed { index, i ->
                when {
                    i == 0 -> return@forEachIndexed
                    last == -1 -> {
                        result = index
                        last = index
                    }

                    else -> {
                        result = result.coerceAtLeast((index - last) / 2)
                        last = index
                    }
                }
            }

            result = result.coerceAtLeast(seats.lastIndex - last)

            return result
        }
    }

    measureTimeMillis {
        Solution().maxDistToClosest(
            intArrayOf(1, 0, 0, 0, 1)
        ).also { println(it) }
        Solution().maxDistToClosest(
            intArrayOf(1, 0, 0, 1)
        ).also { println(it) }
        Solution().maxDistToClosest(
            intArrayOf(1, 0, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}