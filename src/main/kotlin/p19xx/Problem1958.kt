package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkMove(board: Array<CharArray>, rMove: Int, cMove: Int, color: Char): Boolean {
            return arrayOf(
                -1 to 0, 1 to 0, 0 to -1, 0 to 1, -1 to -1, 1 to 1, -1 to 1, 1 to -1
            ).any { (deltaR, deltaC) ->
                var status = 0

                var r = rMove + deltaR
                var c = cMove + deltaC

                while (board.getOrNull(r)?.getOrNull(c)?.takeIf { it != '.' } != null) {
                    val node = board[r][c]

                    when {
                        status == 0 && node == color -> {
                            return@any false
                        }

                        status == 0 && node != color -> {
                            status++
                        }

                        status == 2 -> {
                            return true
                        }

                        node == color -> {
                            status++
                        }
                    }

                    r += deltaR
                    c += deltaC
                }

                status == 2
            }
        }
    }

    measureTimeMillis {
        Solution().checkMove(
            arrayOf(
                charArrayOf('B', 'B', '.', '.', 'B', 'W', 'W', '.'),
                charArrayOf('.', 'W', 'W', '.', 'B', 'W', 'B', 'B'),
                charArrayOf('.', 'W', 'B', 'B', 'W', '.', 'W', '.'),
                charArrayOf('B', '.', '.', 'B', 'W', 'W', 'W', '.'),
                charArrayOf('W', 'W', 'W', 'B', 'W', '.', 'B', 'W'),
                charArrayOf('.', '.', '.', 'W', '.', 'W', '.', 'B'),
                charArrayOf('B', 'B', 'W', 'B', 'B', 'W', 'W', 'B'),
                charArrayOf('W', '.', 'W', 'W', '.', 'B', '.', 'W')
            ), 2, 5, 'W'
        ).also { println("$it should be false") }

        Solution().checkMove(
            arrayOf(
                charArrayOf('W', 'W', '.', 'B', '.', 'B', 'B', '.'),
                charArrayOf('W', 'B', '.', '.', 'W', 'B', '.', '.'),
                charArrayOf('B', 'B', 'B', 'B', 'W', 'W', 'B', '.'),
                charArrayOf('W', 'B', '.', '.', 'B', 'B', 'B', '.'),
                charArrayOf('W', 'W', 'B', '.', 'W', '.', 'B', 'B'),
                charArrayOf('B', '.', 'B', 'W', '.', 'B', '.', '.'),
                charArrayOf('.', 'B', 'B', 'W', 'B', 'B', '.', '.'),
                charArrayOf('B', 'B', 'W', '.', '.', 'B', '.', '.')
            ), 7, 4, 'B'
        ).also { println("$it should be true") }
    }.also { println("Time cost: ${it}ms") }
}