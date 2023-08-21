package p06xx

import util.expect

fun main() {
    class Solution {
        fun findKthNumber(m: Int, n: Int, k: Int): Int {
            fun binarySearch(left: Int, right: Int, k: Int): Int {
                if (left > right) {
                    return Int.MAX_VALUE
                }
                val mid = (left + right) / 2

                var count = mid / m * m

                for (r in mid / m + 1..n.coerceAtMost(mid + 1)) {
                    count += mid / r
                }

                return when {
                    count < k -> binarySearch(mid + 1, right, k)
                    else -> mid.coerceAtMost(binarySearch(left, mid - 1, k))
                }
            }

            return binarySearch(0, m * n, k)
        }
    }

    expect {
        Solution().findKthNumber(
            2, 3, 6
        )
    }
}