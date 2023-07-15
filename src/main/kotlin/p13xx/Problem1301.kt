package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pathsWithMaxScore(board: List<String>): IntArray {
            val m = 1000000007
            val dp = Array(board.size) {
                Array(board[it].length) {
                    intArrayOf(0, 0)
                }
            }

            dp.last().last()[1] = 1

            for (rowIndex in board.lastIndex downTo 0) {
                val row = board[rowIndex]

                loop@ for (columnIndex in row.lastIndex downTo 0) {
                    var score = 0
                    when (val char = row[columnIndex]) {
                        'S', 'X' -> continue@loop
                        'E' -> {}
                        else -> score = char - '0'
                    }

                    val current = dp[rowIndex][columnIndex]
                    arrayOf(
                        rowIndex to columnIndex + 1,
                        rowIndex + 1 to columnIndex,
                        rowIndex + 1 to columnIndex + 1
                    ).forEach { (r, c) ->
                        dp.getOrNull(r)?.getOrNull(c)?.takeIf { it[1] > 0 }?.also { (sum, count) ->
                            when {
                                sum > current[0] -> {
                                    current[0] = sum
                                    current[1] = count
                                }

                                sum == current[0] -> {
                                    current[1] = ((count.toLong() + current[1]) % m).toInt()
                                }
                            }
                        }
                    }

                    current[0] += score
                }
            }

            return dp[0][0]
        }
    }

    measureTimeMillis {
        Solution().pathsWithMaxScore(
            listOf("E12", "1X1", "21S")
        ).toList().also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

