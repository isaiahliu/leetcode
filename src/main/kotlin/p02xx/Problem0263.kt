package p02xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isUgly(n: Int): Boolean {
            if (n < 1) {
                return false
            }

            var t = n.toBigInteger()
            val target = 30.toBigInteger()

            while (true) {
                val gcd = t.gcd(target)

                if (gcd > BigInteger.ONE) {
                    t /= gcd
                } else {
                    break
                }
            }

            return t == BigInteger.ONE
        }
    }

    measureTimeMillis {
        Solution().isUgly(
            1
        ).also { println(it) }
    }
}

