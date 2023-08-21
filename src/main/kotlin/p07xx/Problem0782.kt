package p07xx

import util.expect

fun main() {
    class Solution {
        fun movesToChessboard(board: Array<IntArray>): Int {
            fun IntArray.valid(): Boolean {
                for (i in 1 until size) {
                    if (this[i] == this[i - 1]) {
                        return false
                    }

                }
                return true
            }

            fun Array<IntArray>.switchColumn(columnIndex: Int): Boolean {
                val topRow = this[0]
                for (targetColumn in columnIndex + 1 until topRow.size step 2) {
                    if (topRow[columnIndex] != topRow[targetColumn]) {
                        forEach { row ->
                            val t = row[columnIndex]
                            row[columnIndex] = row[targetColumn]
                            row[targetColumn] = t
                        }
                        return true
                    }
                }

                return false
            }

            fun Array<IntArray>.moveRow(firstNum: Int): Int? {
                var result = 0
                val dupBoard = Array(size) { r ->
                    IntArray(this[r].size) { c ->
                        this[r][c]
                    }
                }

                repeat(dupBoard.size) { rowIndex ->
                    if (dupBoard[rowIndex][0] != (rowIndex % 2) xor firstNum) {
                        var found = false

                        for (targetRowIndex in rowIndex + 1 until dupBoard.size step 2) {
                            if (dupBoard[rowIndex][0] != dupBoard[targetRowIndex][0]) {
                                found = true

                                val t = dupBoard[rowIndex]
                                dupBoard[rowIndex] = dupBoard[targetRowIndex]
                                dupBoard[targetRowIndex] = t

                                result++
                                break
                            }
                        }

                        if (!found) {
                            return null
                        }
                    }

                    if (!dupBoard[rowIndex].valid()) {
                        return null
                    }
                }

                return result
            }

            fun moveColumn(firstNum: Int): Int? {
                var result = 0
                val dupBoard = Array(board.size) { r ->
                    IntArray(board[r].size) { c ->
                        board[r][c]
                    }
                }

                val topRow = dupBoard[0]

                repeat(topRow.size) {
                    if (topRow[it] != (it % 2) xor firstNum) {
                        if (dupBoard.switchColumn(it)) {
                            result++
                        } else {
                            return null
                        }
                    }
                }

                return arrayOf(0, 1).mapNotNull { dupBoard.moveRow(it) }.map { it + result }.minOrNull()
            }

            return arrayOf(moveColumn(0), moveColumn(1)).filterNotNull().minOrNull() ?: -1
        }
    }

    expect {
        Solution().movesToChessboard(
            arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(0, 0, 1),
                intArrayOf(0, 0, 1),
            )
        )
    }
}