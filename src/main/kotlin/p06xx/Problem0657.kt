package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun judgeCircle(moves: String): Boolean {
            var x = 0
            var y = 0

            moves.forEach {
                when (it) {
                    'U' -> y++
                    'D' -> y--
                    'L' -> x--
                    'R' -> x++
                }
            }

            return x == 0 && y == 0
        }
    }

    measureTimeMillis {
        Solution().judgeCircle(
            "UD"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}