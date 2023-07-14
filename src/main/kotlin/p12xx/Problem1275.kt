package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun tictactoe(moves: Array<IntArray>): String {
            val wins = setOf(
                setOf(0 to 0, 0 to 1, 0 to 2),
                setOf(1 to 0, 1 to 1, 1 to 2),
                setOf(2 to 0, 2 to 1, 2 to 2),
                setOf(0 to 0, 1 to 0, 2 to 0),
                setOf(0 to 1, 1 to 1, 2 to 1),
                setOf(0 to 2, 1 to 2, 2 to 2),
                setOf(0 to 0, 1 to 1, 2 to 2),
                setOf(0 to 2, 1 to 1, 2 to 0),
            )

            val route = Array(2) { hashSetOf<Pair<Int, Int>>() }
            var turn = 0

            moves.forEach { (r, c) ->
                route[turn].add(r to c)

                if (wins.any { route[turn].containsAll(it) }) {
                    return ('A' + turn).toString()
                }

                turn = 1 - turn
            }

            return if (moves.size == 9) "Draw" else "Pending"
        }
    }

    measureTimeMillis {
        Solution().tictactoe(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
