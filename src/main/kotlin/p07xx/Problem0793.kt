package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun preimageSizeFZF(k: Int): Int {
            fun binarySearch(start: Long, end: Long, leftBorder: Boolean): Long? {
                if (start > end) {
                    return null
                }

                val mid = (start + end) / 2
                var result = 0L

                var t = mid / 5

                while (t > 0) {
                    result += t
                    t /= 5
                }

                return when {
                    result < k -> binarySearch(mid + 1, end, leftBorder)
                    result > k -> binarySearch(start, mid - 1, leftBorder)
                    leftBorder -> binarySearch(start, mid - 1, true) ?: mid
                    else -> binarySearch(mid + 1, end, false) ?: mid
                }
            }

            val MAX = 4500000000L

            return binarySearch(0L, MAX, false)?.let { r ->
                binarySearch(0L, r, true)?.let { l ->
                    (r - l + 1).toInt()
                }
            } ?: 0
        }
    }

    measureTimeMillis {
        Solution().preimageSizeFZF(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}