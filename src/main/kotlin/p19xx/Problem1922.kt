package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countGoodNumbers(n: Long): Int {
            val m = 1000000007.toBigInteger()

            return (5.toBigInteger().modPow((n / 2 + n % 2).toBigInteger(), m) * 4.toBigInteger()
                .modPow((n / 2).toBigInteger(), m) % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().countGoodNumbers(
            1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}