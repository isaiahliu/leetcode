package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthFactor(n: Int, k: Int): Int {
            var count = 0

            repeat(n) {
                if (n % (it + 1) == 0) {
                    count++
                }

                if (count == k) {
                    return it + 1
                }
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().kthFactor(
            7, 2
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

