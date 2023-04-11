package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isRobotBounded(instructions: String): Boolean {
            var x = 0
            var y = 0

            var deltaX = 0
            var deltaY = 1

            instructions.forEach {
                when (it) {
                    'G' -> {
                        x += deltaX
                        y += deltaY
                    }

                    'L' -> {
                        val t = deltaX
                        deltaX = -deltaY
                        deltaY = t
                    }

                    'R' -> {
                        val t = deltaY
                        deltaY = -deltaX
                        deltaX = t
                    }
                }
            }

            return when {
                x == 0 && y == 0 -> true
                deltaX == 0 && deltaY == 1 -> false
                else -> true
            }
        }
    }

    measureTimeMillis {
        Solution().isRobotBounded(
            "GG"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}