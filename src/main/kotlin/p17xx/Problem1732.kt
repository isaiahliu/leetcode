package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestAltitude(gain: IntArray): Int {
            var result = 0

            gain.fold(0) { a, b ->
                (a + b).also { result = result.coerceAtLeast(it) }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().largestAltitude(
            intArrayOf(-5, 1, 5, 0, -7)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
