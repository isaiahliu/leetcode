package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun circularGameLosers(n: Int, k: Int): IntArray {
            val players = sortedSetOf<Int>()
            repeat(n - 1) {
                players.add(it + 1)
            }

            var player = 0
            var add = k
            while (true) {
                player += add
                player %= n
                add += k

                if (!players.remove(player)) {
                    break
                }
            }

            return players.map { it + 1 }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().circularGameLosers(
            5, 2
        ).toList().also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
