package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validTicTacToe(board: Array<String>): Boolean {
            val players = charArrayOf('X', 'O')

            val counts = intArrayOf(0, 0)
            val wins = intArrayOf(0, 0)

            val connects = Array(3) { r ->
                Array(3) { c ->
                    (if (board[r][c] == ' ') 0 else 1).let { s ->
                        IntArray(4) { s }
                    }
                }
            }
            board.forEachIndexed { r, row ->
                row.forEachIndexed { c, player ->
                    if (player != ' ') {
                        val index = players.indexOf(player)

                        counts[index]++

                        val connect = connects[r][c]
                        if (board.getOrNull(r - 1)?.getOrNull(c) == player) {
                            connect[0] += connects[r - 1][c][0]
                        }

                        if (board.getOrNull(r)?.getOrNull(c - 1) == player) {
                            connect[1] += connects[r][c - 1][1]
                        }

                        if (board.getOrNull(r - 1)?.getOrNull(c + 1) == player) {
                            connect[2] += connects[r - 1][c + 1][2]
                        }

                        if (board.getOrNull(r - 1)?.getOrNull(c - 1) == player) {
                            connect[3] += connects[r - 1][c - 1][3]
                        }

                        wins[index] += connect.count { it == 3 }
                    }
                }
            }

            val deltaCount = counts[0] - counts[1]
            return when {
                wins[0] > 0 && wins[1] > 0 -> false
                wins[0] > 0 -> deltaCount == 1
                wins[1] > 0 -> deltaCount == 0
                else -> deltaCount in 0..1
            }
        }
    }

    measureTimeMillis {
        Solution().validTicTacToe(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}