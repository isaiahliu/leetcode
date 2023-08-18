package p19xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minNonZeroProduct(p: Int): Int {
            val m = 1000000007.toBigInteger()

            val num = ((1L shl p) - 1).toBigInteger()

            return (num * (num - BigInteger.ONE).modPow(((1L shl (p - 1)) - 1).toBigInteger(), m) % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().minNonZeroProduct(
            33
        ).also { println("$it should be 581202553") }
    }.also { println("Time cost: ${it}ms") }
}