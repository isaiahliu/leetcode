package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun baseNeg2(n: Int): String {
            if (n == 0) {
                return "0"
            }

            var digitCount = 1
            var bit = 1

            if (n < 0) {
                bit *= -2
                digitCount++
            }

            var sum = bit

            while (n > 0 && sum < n || n < 0 && sum > n) {
                digitCount += 2
                bit *= 4
                sum += bit
            }

            return "1" + baseNeg2(n - bit).trimStart('0').padStart(digitCount - 1, '0')
        }
    }

    measureTimeMillis {
        Solution().baseNeg2(
            9
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}