package p12xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nthUglyNumber(n: Int, a: Int, b: Int, c: Int): Int {
            if (a == 1 || b == 1 || c == 1) {
                return n
            }

            val target = n.toBigInteger()

            val ai = a.toBigInteger()
            val bi = b.toBigInteger()
            val ci = c.toBigInteger()
            val ab = (ai * bi / ai.gcd(bi))
            val bc = (bi * ci / bi.gcd(ci))
            val ac = (ai * ci / ai.gcd(ci))
            val abc = (ab * ci / ab.gcd(ci))

            fun binarySearch(start: BigInteger, end: BigInteger): Int {
                if (start > end) {
                    return Int.MAX_VALUE
                }

                val mid = (end + start) / 2.toBigInteger()

                var r = BigInteger.ZERO
                r += mid / ai
                r += mid / bi
                r += mid / ci
                r -= mid / ab
                r -= mid / bc
                r -= mid / ac
                r += mid / abc

                return when {
                    r < target -> binarySearch(mid + BigInteger.ONE, end)
                    r > target -> binarySearch(start, mid - BigInteger.ONE)
                    else -> mid.toInt().coerceAtMost(binarySearch(start, mid - BigInteger.ONE))
                }
            }

            return binarySearch(BigInteger.ONE, Int.MAX_VALUE.toBigInteger())
        }
    }

    measureTimeMillis {
        Solution().nthUglyNumber(
            1000000000, 2, 217983653, 336916467
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
