package p19xx

import util.expect

fun main() {
    class Solution {
        fun minimumPerimeter(neededApples: Long): Long {
            fun binarySearch(min: Long, max: Long): Long {
                if (min > max) {
                    return Long.MAX_VALUE
                }

                val mid = (min + max) / 2

                return if (2 * mid * (mid + 1) * (mid * 2 + 1) < neededApples) {
                    binarySearch(mid + 1, max)
                } else {
                    mid.coerceAtMost(binarySearch(min, mid - 1))
                }
            }

            return binarySearch(1L, 100000L) * 8
        }
    }

    expect {
        Solution().minimumPerimeter(
            5L
        )
    }
}