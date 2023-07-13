package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int {
            if (a == 1 || b == 1 || c == 1) {
                return n
            }

            val ai = a.toBigInteger()
            val bi = b.toBigInteger()
            val ci = c.toBigInteger()
            val ab = (ai * bi / ai.gcd(bi)).toLong()
            val bc = (bi * ci / bi.gcd(ci)).toLong()
            val ac = (ai * ci / ai.gcd(ci)).toLong()
            val abc = (ab.toBigInteger() * ci / ab.toBigInteger().gcd(ci)).toLong()

            fun binarySearch(start: Long, end: Long): Long {
                if (start > end) {
                    return Long.MAX_VALUE
                }

                val mid = start + (end - start) / 2

                var r = 0L
                r += mid / a
                r += mid / b
                r += mid / c
                r -= mid / ab
                r -= mid / bc
                r -= mid / ac
                r += mid / abc

                return when {
                    r < n -> binarySearch(mid + 1, end)
                    r > n -> binarySearch(start, mid - 1)
                    else -> mid.coerceAtMost(binarySearch(start, mid - 1))
                }
            }

            return binarySearch(1L, Int.MAX_VALUE.toLong()).toInt()
        }
    }

    measureTimeMillis {
        Solution().nthUglyNumber(
            1000000000, 2, 217983653, 336916467
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
