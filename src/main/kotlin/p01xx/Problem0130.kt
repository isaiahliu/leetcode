package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun solve(board: Array<CharArray>): Unit {
            val innerO = arrayListOf<Pair<Int, Int>>()
            val outerO = arrayListOf<Pair<Int, Int>>()

            board.forEachIndexed { r, row ->
                row.forEachIndexed { c, ch ->
                    if (ch == 'O') {
                        if (r in 1 until board.lastIndex && c in 1 until row.lastIndex) {
                            innerO
                        } else {
                            outerO
                        }.add(r to c)
                    }
                }
            }

            while (outerO.isNotEmpty()) {
                outerO.toList().also { outerO.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter { it in innerO }.forEach {
                        outerO.add(it)
                        innerO.remove(it)
                    }
                }
            }

            innerO.forEach { (r, c) ->
                board[r][c] = 'X'
            }
        }
    }

    measureTimeMillis {
        Solution().solve(
            arrayOf(
                charArrayOf('X', 'X', 'X', 'X'),
                charArrayOf('X', 'O', 'O', 'X'),
                charArrayOf('X', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'X', 'X'),
            )
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

