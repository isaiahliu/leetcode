package p04xx

import util.expect

fun main() {
    class Solution {
        fun arrangeCoins(n: Int): Int {
            fun binarySearch(left: Long, right: Long): Long? {
                if (left > right) {
                    return null
                }

                val mid = (left + right) / 2

                val num = mid * (mid + 1) / 2

                return if (num == n.toLong()) {
                    mid
                } else if (num < n) {
                    binarySearch(mid + 1, right) ?: mid
                } else {
                    binarySearch(left, mid - 1)
                }
            }

            return binarySearch(1L, 1L shl 30)?.toInt() ?: 0
        }
    }

    expect {
        Solution().arrangeCoins(
            1804289383
        )
    }
}


