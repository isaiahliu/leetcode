package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun twoEggDrop(n: Int): Int {
            fun binarySearch(min: Int, max: Int): Int {
                if (min > max) {
                    return Int.MAX_VALUE
                }

                val mid = (min + max) / 2

                return if (mid * (mid + 1) / 2 < n) {
                    binarySearch(mid + 1, max)
                } else {
                    mid.coerceAtMost(binarySearch(min, mid - 1))
                }
            }
            return binarySearch(1, n)
        }
    }

    measureTimeMillis {
        Solution().twoEggDrop(
            5
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
