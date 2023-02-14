package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isValidSudoku(board: Array<CharArray>): Boolean {
            val range = 0 until 9
            board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    if (c != '.') {
                        if (range.count { board[rowIndex][it] == c } > 1) {
                            return false
                        }

                        if (range.count { board[it][columnIndex] == c } > 1) {
                            return false
                        }

                        val gridRow = rowIndex / 3 * 3
                        val columnRow = columnIndex / 3 * 3
                        if (range.count {
                                board[gridRow + it / 3][columnRow + it % 3] == c
                            } > 1) {
                            return false
                        }
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
//        println(Solution().searchInsert(intArrayOf(1, 3, 5, 6), 0))
    }.also { println("Time cost: ${it}ms") }
}


