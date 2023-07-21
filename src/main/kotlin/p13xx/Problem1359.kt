package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val m = 1000000007

        fun countOrders(n: Int): Int {
            if (n == 1) {
                return 1
            }

            return (n.toLong() * countOrders(n - 1) * ((n - 1) * 2 + 1) % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().countOrders(
            2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

