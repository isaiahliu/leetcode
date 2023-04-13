package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rotatedDigits(n: Int): Int {
            val good = intArrayOf(2, 5, 6, 9)
            val bad = intArrayOf(3, 4, 7)
            return (1..n).count {
                var r = false

                var t = it
                while (t > 0) {
                    when (t % 10) {
                        in good -> r = true
                        in bad -> return@count false
                    }

                    t /= 10
                }

                r
            }
        }
    }

    measureTimeMillis {
        Solution().rotatedDigits(
            10
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}