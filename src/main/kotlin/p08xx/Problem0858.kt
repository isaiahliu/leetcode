package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mirrorReflection(p: Int, q: Int): Int {
            var t = 0

            var times = 0
            var delta = q

            while (true) {
                t += delta
                times = 1 - times
                when {
                    t == 0 -> return 0
                    t == p -> return 2 - times
                    t > p -> {
                        t = p + p - t
                        delta = -delta
                    }

                    t < 0 -> {
                        t = -t
                        delta = -delta
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().mirrorReflection(
            3, 2
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}