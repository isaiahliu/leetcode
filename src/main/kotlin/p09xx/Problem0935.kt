package p09xx

import util.expect

fun main() {
    class Solution {
        fun knightDialer(n: Int): Int {
            val m = 1000000007L

            val dp = Array(n) { i ->
                LongArray(10) {
                    if (i == 0) {
                        1L
                    } else {
                        0L
                    }
                }
            }

            val moves = arrayOf(
                intArrayOf(4, 6),
                intArrayOf(6, 8),
                intArrayOf(7, 9),
                intArrayOf(4, 8),
                intArrayOf(3, 9, 0),
                intArrayOf(),
                intArrayOf(1, 7, 0),
                intArrayOf(2, 6),
                intArrayOf(1, 3),
                intArrayOf(2, 4),
            )
            for (turn in 1 until n) {
                moves.forEachIndexed { index, froms ->
                    froms.forEach {
                        dp[turn][index] += dp[turn - 1][it]
                    }

                    dp[turn][index] %= m
                }
            }

            return (dp[dp.lastIndex].sum() % m).toInt()
        }
    }
    expect {
        Solution().knightDialer(
            1
        )
    }
}