package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findKthNumber(m: Int, n: Int, k: Int): Int {
            fun binarySearch(left: Int, right: Int, k: Int): Int {
                if (left > right) {
                    return Int.MAX_VALUE
                }
                val mid = (left + right) / 2

                var count = 0

                for (r in 1..n) {
                    val countInRow = (mid / r).coerceAtMost(m)

                    if (countInRow == 0) {
                        break
                    }

                    count += countInRow
                }

                return when {
                    count < k -> binarySearch(mid + 1, right, k)
                    else -> mid.coerceAtMost(binarySearch(left, mid - 1, k))
                }
            }

            return binarySearch(0, m * n, k)
        }
    }

    measureTimeMillis {
        Solution().findKthNumber(
            2, 3, 6
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}