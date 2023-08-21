package p08xx

import util.expect

fun main() {
    class Solution {
        fun nthMagicalNumber(n: Int, a: Int, b: Int): Int {
            val m = 1000000007
            if (a == b) {
                return ((a.toLong() * n) % m).toInt()
            }

            val gcd = a.toBigInteger().gcd(b.toBigInteger()).toLong()

            val min = (a.coerceAtMost(b)).toLong()
            val max = (a.coerceAtLeast(b)).toLong()

            if (n == 1) {
                return min.toInt()
            }

            fun binarySearch(start: Long, end: Long): Long {
                val mid = start + (end - start) / 2

                val count = mid / min + mid / max - mid / (min * max / gcd)

                return when {
                    count < n -> {
                        binarySearch(mid + 1, end)
                    }

                    count > n -> {
                        binarySearch(start, mid - 1)
                    }

                    else -> {
                        (mid / min * min).coerceAtLeast(mid / max * max)
                    }
                }
            }

            return (binarySearch(min, (max * n)) % m).toInt()
        }
    }

    expect {
        Solution().nthMagicalNumber(
            5, 2, 4
        )

    }
}