package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheWinner(n: Int, k: Int): Int {
            val team = arrayListOf<Int>()
            repeat(n) {
                team.add(it + 1)
            }

            var index = 0

            while (team.size > 1) {
                index = (index + k - 1) % team.size

                team.removeAt(index)
            }

            return team[0]
        }
    }

    measureTimeMillis {
        Solution().findTheWinner(
            5, 2
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
