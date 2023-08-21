package p18xx

import util.expect

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

    expect {
        Solution().twoEggDrop(
            5
        )
    }
}
