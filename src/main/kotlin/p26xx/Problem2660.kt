package p26xx

import util.expect

fun main() {
    class Solution {
        fun isWinner(player1: IntArray, player2: IntArray): Int {
            fun IntArray.score(): Int {
                var sum = 0

                var maxScoreTurn = 0
                forEach {
                    sum += it
                    if (maxScoreTurn > 0) {
                        sum += it
                    }

                    if (it == 10) {
                        maxScoreTurn = 2
                    } else {
                        maxScoreTurn--
                    }
                }

                return sum
            }

            return player1.score().compareTo(player2.score()).takeIf { it >= 0 } ?: 2
        }
    }

    expect {
        Solution().isWinner(
            intArrayOf(), intArrayOf()
        )
    }
}