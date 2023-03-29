package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distributeCandies(candyType: IntArray): Int {
            return candyType.distinct().size.coerceAtMost(candyType.size / 2)
        }
    }

    measureTimeMillis {
        Solution().distributeCandies(
            intArrayOf(1, 1, 2, 3)
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}