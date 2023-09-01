package p22xx

import util.expect

fun main() {
    class Solution {
        fun findWinners(matches: Array<IntArray>): List<List<Int>> {
            val lose0 = sortedSetOf<Int>()
            val lose1 = sortedSetOf<Int>()

            val visited = hashSetOf<Int>()
            matches.forEach { (win, lose) ->
                if (visited.add(win)) {
                    lose0.add(win)
                }

                if (visited.add(lose) || lose0.remove(lose)) {
                    lose1.add(lose)
                } else {
                    lose1.remove(lose)
                }
            }

            return listOf(lose0.toList(), lose1.toList())
        }
    }

    expect {
        Solution().findWinners(
            arrayOf()
        )
    }
}