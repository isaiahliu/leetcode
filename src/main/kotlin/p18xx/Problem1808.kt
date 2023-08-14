package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxNiceDivisors(primeFactors: Int): Int {
            return primeFactors.takeIf { it > 4 }?.let {
                var count = it / 3
                var rem = it % 3
                if (rem <= 1) {
                    rem += 3
                    count -= 1
                }

                val m = 1000000007.toBigInteger()
                ((3.toBigInteger().modPow(count.toBigInteger(), m) * rem.toBigInteger()) % m).toInt()
            } ?: primeFactors
        }
    }

    measureTimeMillis {
        Solution().maxNiceDivisors(
            5
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
