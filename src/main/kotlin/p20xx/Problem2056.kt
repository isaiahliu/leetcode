package p20xx

import util.expect

fun main() {
    class Solution {
        fun countCombinations(pieces: Array<String>, positions: Array<IntArray>): Int {
            val range = 0 until 8

            fun Int.move(movement: Pair<Int, Int>): Int? {
                val (deltaR, deltaC) = movement

                val r = this / 8 + deltaR
                val c = this % 8 + deltaC

                return (r * 8 + c).takeIf { r in range && c in range }
            }

            val deltas = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1, -1 to -1, -1 to 1, 1 to -1, 1 to 1)
            val types = mapOf(
                "rook" to (0 until 4),
                "bishop" to (4 until 8),
                "queen" to (0 until 8),
            )

            val charMoves = pieces.mapNotNull {
                types[it]
            }

            val init = positions.map { (r, c) ->
                (r - 1) * 8 + c - 1
            }

            var result = 0

            fun dfs(movements: List<Int>, pos: List<Int>, status: Int) {
                val nextPos = arrayListOf<Int>()

                for ((index, p) in pos.withIndex()) {
                    if (status and (1 shl index) > 0) {
                        p.move(deltas[movements[index]])?.also { nextPos.add(it) } ?: return
                    } else {
                        nextPos.add(p)
                    }
                }

                if (nextPos.distinct().size == nextPos.size) {
                    result++

                    for (nextStatus in 1 until (1 shl init.size)) {
                        if (nextStatus and status == nextStatus) {
                            dfs(movements, nextPos, nextStatus)
                        }
                    }
                }
            }

            fun arrangeMoves(status: Int, movements: List<Int>) {
                when {
                    movements.size == charMoves.size -> {
                        dfs(movements, init, status)
                    }

                    (1 shl movements.size) and status == 0 -> {
                        arrangeMoves(status, movements + 0)
                    }

                    else -> {
                        charMoves[movements.size].forEach {
                            arrangeMoves(status, movements + it)
                        }
                    }
                }
            }

            repeat(1 shl init.size) {
                arrangeMoves(it, emptyList())
            }

            return result
        }
    }

    expect {
        Solution().countCombinations(
            arrayOf("rook", "rook"), arrayOf(
                intArrayOf(1, 1),
                intArrayOf(8, 8),
            )
        )
    }
    expect(281) {
        Solution().countCombinations(
            arrayOf("queen", "bishop"), arrayOf(
                intArrayOf(5, 7), intArrayOf(3, 4)
            )
        )
    }
}