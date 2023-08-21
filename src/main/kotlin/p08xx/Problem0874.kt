package p08xx

import util.expect

fun main() {
    class Solution {
        fun robotSim(commands: IntArray, obstacles: Array<IntArray>): Int {
            val set = obstacles.map { (x, y) -> x to y }.toSet()

            var x = 0
            var y = 0

            var deltaX = 0
            var deltaY = 1

            var result = 0
            commands.forEach {
                when (it) {
                    -2 -> {
                        val t = deltaY
                        deltaY = deltaX
                        deltaX = -t
                    }

                    -1 -> {
                        val t = deltaX
                        deltaX = deltaY
                        deltaY = -t
                    }

                    else -> {
                        for (step in 0 until it) {
                            x += deltaX
                            y += deltaY

                            if (x to y in set) {
                                x -= deltaX
                                y -= deltaY

                                break
                            }

                            result = result.coerceAtLeast(x * x + y * y)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().robotSim(
            intArrayOf(4, -1, 3), arrayOf()
        )

    }
}