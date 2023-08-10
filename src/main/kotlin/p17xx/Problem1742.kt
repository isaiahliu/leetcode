package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countBalls(lowLimit: Int, highLimit: Int): Int {
            return (lowLimit..highLimit).groupingBy {
                var t = it
                var r = 0
                while (t > 0) {
                    r += t % 10
                    t /= 10
                }

                r
            }.eachCount().values.max()
        }
    }

    measureTimeMillis {
        Solution().countBalls(
            5, 15
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
