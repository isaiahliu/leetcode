package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numRookCaptures(board: Array<CharArray>): Int {
            var result = 0

            board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    if (c == 'R') {
                        arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).forEach { (dr, dc) ->
                            var r = rowIndex + dr
                            var c = columnIndex + dc

                            loop@ while (true) {
                                when (board.getOrNull(r)?.getOrNull(c)) {
                                    null, 'B' -> break@loop
                                    'p' -> {
                                        result++
                                        break@loop
                                    }
                                }

                                r += dr
                                c += dc
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numRookCaptures(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
