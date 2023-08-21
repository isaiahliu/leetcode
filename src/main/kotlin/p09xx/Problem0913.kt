package p09xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        val MOUSE_TURN = 0
        val CAT_TURN = 1
        val DRAW = 0
        val MOUSE_WIN = 1
        val CAT_WIN = 2

        fun catMouseGame(graph: Array<IntArray>): Int {
            val n = graph.size
            val positions = Array(n) { mousePos ->
                Array(n) { catPos ->
                    (catPos.takeIf { it > 0 }?.let { graph[it].size } ?: 0).let {
                        intArrayOf(graph[mousePos].size, graph[catPos].size)
                    }
                }
            }

            graph[0].forEach { g ->
                repeat(n) {
                    positions[it][g][CAT_TURN]--
                }
            }

            val result = Array(n) { Array(n) { IntArray(2) } }

            val tasks = LinkedList<Pair<Pair<Int, Int>, Int>>()
            repeat(graph.size - 1) {
                result[0][it + 1][MOUSE_TURN] = MOUSE_WIN
                result[0][it + 1][CAT_TURN] = MOUSE_WIN
                result[it + 1][it + 1][MOUSE_TURN] = CAT_WIN
                result[it + 1][it + 1][CAT_TURN] = CAT_WIN

                tasks.add(0 to it + 1 to MOUSE_TURN)
                tasks.add(0 to it + 1 to CAT_TURN)
                tasks.add(it + 1 to it + 1 to MOUSE_TURN)
                tasks.add(it + 1 to it + 1 to CAT_TURN)
            }

            while (tasks.isNotEmpty()) {
                val (p, turn) = tasks.poll()
                val (mousePos, catPos) = p

                val previousStatus = if (turn == MOUSE_TURN) {
                    graph[catPos].map { from ->
                        from.takeIf { it > 0 }?.let { mousePos to it }
                    }.filterNotNull()
                } else {
                    graph[mousePos].map { from ->
                        from to catPos
                    }
                }

                val currentResult = result[mousePos][catPos][turn]
                val lastTurn = 1 - turn

                previousStatus.forEach { (m, c) ->
                    if (result[m][c][lastTurn] == DRAW) {
                        if (currentResult == MOUSE_WIN && lastTurn == MOUSE_TURN || currentResult == CAT_WIN && lastTurn == CAT_TURN) {
                            result[m][c][lastTurn] = currentResult
                            tasks.add(m to c to lastTurn)
                        } else {
                            positions[m][c][lastTurn]--

                            if (positions[m][c][lastTurn] == 0) {
                                result[m][c][lastTurn] = if (lastTurn == MOUSE_TURN) CAT_WIN else MOUSE_WIN
                                tasks.add(m to c to lastTurn)
                            }
                        }
                    }
                }
            }

            return result[1][2][MOUSE_TURN]
        }
    }

    expect {
        Solution().catMouseGame(
            arrayOf(
                intArrayOf(2, 3), intArrayOf(2), intArrayOf(0, 1), intArrayOf(0, 4), intArrayOf(3)
            )
        )
    }
}