package p08xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minEatingSpeed(piles: IntArray, h: Int): Int {
            val max = piles.max()

            fun binarySearch(start: Int, end: Int): Int {
                if (start > end) {
                    return max
                }

                val mid = start + (end - start) / 2

                val r = piles.map { (it / mid + (it % mid).sign).toLong() }.sum()

                return when {
                    r > h -> binarySearch(mid + 1, end)
                    else -> binarySearch(start, mid - 1).coerceAtMost(mid)
                }
            }

            return binarySearch(1, max)
        }
    }

    measureTimeMillis {
        Solution().minEatingSpeed(
            intArrayOf(805306368, 805306368, 805306368), 1000000000
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}