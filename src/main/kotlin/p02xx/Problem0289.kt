package p02xx

import util.expect

fun main() {
    class Solution {
        fun gameOfLife(board: Array<IntArray>): Unit {
            board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, i ->
                    val liveNeighbors = arrayOf(
                        rowIndex - 1 to columnIndex - 1,
                        rowIndex - 1 to columnIndex,
                        rowIndex - 1 to columnIndex + 1,
                        rowIndex to columnIndex - 1,
                        rowIndex to columnIndex + 1,
                        rowIndex + 1 to columnIndex - 1,
                        rowIndex + 1 to columnIndex,
                        rowIndex + 1 to columnIndex + 1,
                    ).mapNotNull { (r, c) -> board.getOrNull(r)?.getOrNull(c) }.count {
                        it and 1 > 0
                    }

                    val newState = when (i) {
                        0 -> {
                            liveNeighbors == 3
                        }

                        else -> {
                            liveNeighbors in 2..3
                        }
                    }

                    if (newState) {
                        row[columnIndex] = i + 0b10
                    }
                }

                if (rowIndex > 0) {
                    board[rowIndex - 1].also {
                        it.forEachIndexed { cIndex, n ->
                            it[cIndex] = n shr 1
                        }
                    }
                }
            }

            board[board.lastIndex].also {
                it.forEachIndexed { cIndex, n ->
                    it[cIndex] = n shr 1
                }
            }
        }
    }

    expect {
        Solution().gameOfLife(emptyArray())
    }
}

