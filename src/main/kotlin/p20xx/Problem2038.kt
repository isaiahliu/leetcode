package p20xx

import util.expect

fun main() {
    class Solution {
        fun winnerOfGame(colors: String): Boolean {
            val counts = intArrayOf(0, 0)

            var count = 0
            var pre = colors[0]

            "$colors ".forEach {
                if (it == pre) {
                    count++
                } else {
                    counts[pre - 'A'] += (count - 2).coerceAtLeast(0)

                    pre = it
                    count = 1
                }
            }

            return counts[0] > counts[1]
        }
    }

    expect {
        Solution().winnerOfGame(
            ""
        )
    }
}