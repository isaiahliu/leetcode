package p02xx

import kotlin.system.measureTimeMillis

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

            board[board.size - 1].also {
                it.forEachIndexed { cIndex, n ->
                    it[cIndex] = n shr 1
                }
            }
        }
    }

    measureTimeMillis {
        Solution().gameOfLife(emptyArray()).also { println(it) }
    }
}

