package p24xx

import util.expect

fun main() {
    class Solution {
        fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
            players.sort()
            trainers.sort()

            var result = 0

            var l1 = 0
            var l2 = 0

            while (l1 < players.size && l2 < trainers.size) {
                if (players[l1] <= trainers[l2]) {
                    l1++
                    l2++
                    result++
                } else {
                    l2++
                }
            }

            return result
        }
    }

    expect {
        Solution().matchPlayersAndTrainers(
            intArrayOf(), intArrayOf()
        )
    }
}