package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subtractProductAndSum(n: Int): Int {
            var add = 0
            var mul = 1

            var t = n
            while (t > 0) {
                (t % 10).also {
                    add += it
                    mul *= it
                }

                t /= 10
            }

            return mul - add
        }
    }

    measureTimeMillis {
        Solution().subtractProductAndSum(
            1234
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
