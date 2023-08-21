package p05xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
            val clickTask = LinkedList(listOf(click))

            while (clickTask.isNotEmpty()) {
                val (r, c) = clickTask.pop()

                when (board.getOrNull(r)?.getOrNull(c)) {
                    'M' -> {
                        board[r][c] = 'X'
                        return board
                    }

                    'E' -> {
                        val around = arrayOf(
                            r - 1 to c - 1, r - 1 to c, r - 1 to c + 1,
                            r to c - 1, r to c + 1,
                            r + 1 to c - 1, r + 1 to c, r + 1 to c + 1
                        )

                        val mineCount =
                            around.mapNotNull { board.getOrNull(it.first)?.getOrNull(it.second) }.count { it == 'M' }

                        if (mineCount == 0) {
                            board[r][c] = 'B'

                            clickTask.addAll(around.map { intArrayOf(it.first, it.second) })
                        } else {
                            board[r][c] = '0' + mineCount
                        }
                    }
                }
            }

            return board
        }
    }

    expect {
        Solution().updateBoard(arrayOf(), intArrayOf())
    }
}