package p16xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfSets(n: Int, k: Int): Int {
            println(5.toBigInteger().modInverse(3.toBigInteger()))

            val m = 1000000007
            val mi = m.toBigInteger()

            fun Int.factorial(): BigInteger {
                var result = 1L

                repeat(this) {
                    result *= it + 1
                    result %= m
                }

                return result.toBigInteger()
            }


            return ((n + k - 1).factorial() * (k * 2).factorial().modInverse(mi) * (n - k - 1).factorial()
                .modInverse(mi) % mi).toInt()
        }
    }

    measureTimeMillis {
        Solution().numberOfSets(
            633, 64
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}