package plcp

import util.expect

fun main() {
    class Solution {
        fun flipChess(chessboard: Array<String>): Int {
            val BLACK = 'X'
            val WHITE = 'O'
            val EMPTY = '.'

            val around = arrayOf(
                -1 to -1,
                -1 to 0,
                -1 to 1,
                0 to -1,
                0 to 1,
                1 to -1,
                1 to 0,
                1 to 1
            )

            val initStatus = chessboard.joinToString("")

            val initialWhiteCount = initStatus.count { it == WHITE }

            val rowSize = chessboard[0].length

            operator fun String.get(rowIndex: Int, columnIndex: Int): Char? {
                return if (rowIndex !in chessboard.indices || columnIndex !in 0 until rowSize) {
                    null
                } else {
                    this.getOrNull(rowIndex * rowSize + columnIndex)
                }
            }

            fun String.flip(vararg pos: Pair<Int, Int>): String {
                val chars = this.toCharArray()

                pos.forEach { (r, c) ->
                    chars[r * rowSize + c] = 'X'
                }

                return String(chars)
            }

            fun String.process(rowIndex: Int, columnIndex: Int): String {
                var status = this.flip(rowIndex to columnIndex)

                val replacedNodes = hashSetOf<Pair<Int, Int>>()

                around.forEach { (deltaR, deltaC) ->
                    val temp = hashSetOf<Pair<Int, Int>>()

                    var r = rowIndex + deltaR
                    var c = columnIndex + deltaC
                    loop@ while (true) {
                        when (status[r, c]) {
                            BLACK -> {
                                replacedNodes.addAll(temp)
                                break@loop
                            }

                            WHITE -> {
                                temp += r to c
                                r += deltaR
                                c += deltaC
                            }

                            else -> {
                                break@loop
                            }
                        }
                    }
                }

                status = status.flip(*replacedNodes.toTypedArray())

                replacedNodes.forEach {
                    status = status.process(it.first, it.second)
                }

                return status
            }

            val nodes = hashSetOf<Pair<Int, Int>>()
            chessboard.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, ch ->
                    if (ch == WHITE) {
                        around.filter { (deltaR, deltaC) ->
                            initStatus[rowIndex + deltaR, columnIndex + deltaC] == EMPTY
                        }.forEach { (deltaR, deltaC) ->
                            nodes.add(rowIndex + deltaR to columnIndex + deltaC)
                        }
                    }
                }
            }

            var result = initialWhiteCount
            nodes.forEach {
                result = result.coerceAtMost(initStatus.process(it.first, it.second).count { it == WHITE })
            }

            return initialWhiteCount - result
        }
    }

    expect {
        Solution().flipChess(
            arrayOf(
                "....X.", "....X.", "XOOO..", "......", "......"
            )
        )
    }
}

