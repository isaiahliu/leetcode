package p18xx

import util.expect

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

    expect {
        Solution().findTheWinner(
            5, 2
        )
    }
}
