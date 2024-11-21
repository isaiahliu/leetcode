package p32xx

import util.expect

fun main() {
    class Solution {
        fun finalPositionOfSnake(n: Int, commands: List<String>): Int {
            return commands.sumOf {
                when (it) {
                    "UP" -> -n
                    "DOWN" -> n
                    "LEFT" -> -1
                    else -> 1
                }
            }
        }
    }

    expect {
        Solution().finalPositionOfSnake(
            4, listOf()
        )
    }
}
