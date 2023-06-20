package p27xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flipChess(chessboard: Array<String>): Int {
            val BLACK = 'X'
            val WHITE = 'O'
            val EMPTY = '.'

            val initStatus = chessboard.joinToString("")

            val rowSize = chessboard[0].length

            operator fun String.get(rowIndex: Int, columnIndex: Int): Char? {
                return if (columnIndex >= rowSize) {
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

            fun String.process(rowIndex: Int, columnIndex: Int): Pair<String, Int> {
                var status = this.flip(rowIndex to columnIndex)

                val replacedNodes = hashSetOf<Pair<Int, Int>>()
                arrayOf(
                    -1 to -1,
                    -1 to 0,
                    -1 to 1,
                    0 to -1,
                    0 to 1,
                    1 to -1,
                    1 to 0,
                    1 to 1
                ).forEach { (deltaR, deltaC) ->
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

                var result = replacedNodes.size

                status = status.flip(*replacedNodes.toTypedArray())

                replacedNodes.forEach {
                    status.process(it.first, it.second).also {
                        status = it.first
                        result += it.second
                    }
                }

                return status to result
            }

            var result = 0
            chessboard.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, ch ->
                    if (ch == EMPTY) {
                        val p = initStatus.process(rowIndex, columnIndex)
                        result = result.coerceAtLeast(p.second)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().flipChess(
            arrayOf(
                ".....",
                ".....",
                "X....",
                "OX...",
                "OOOOX",
                "OOO..",
                ".OO..",
                "X..X."
            )
        ).also { println(it) }
    }
}

