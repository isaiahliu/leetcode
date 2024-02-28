package p25xx

import util.expect

fun main() {
    class Solution {
        fun rootCount(edges: Array<IntArray>, guesses: Array<IntArray>, k: Int): Int {
            val adjacent = Array(edges.size + 1) { hashSetOf<Int>() }

            edges.forEach { (from, to) ->
                adjacent[from] += to
                adjacent[to] += from
            }

            val guess = Array(edges.size + 1) { hashSetOf<Int>() }
            guesses.forEach { (from, to) ->
                guess[from] += to
            }

            val guessVisited = hashSetOf(0)
            fun dfsGuess(node: Int): Int {
                var result = 0
                adjacent[node].forEach { next ->
                    if (guessVisited.add(next)) {
                        if (next in guess[node]) {
                            result++
                        }

                        result += dfsGuess(next)
                    }
                }

                return result
            }

            val dpVisited = hashSetOf(0)
            fun dfsDp(node: Int, correctCount: Int): Int {
                var result = 0
                if (correctCount >= k) {
                    result++
                }

                adjacent[node].forEach { next ->
                    if (dpVisited.add(next)) {
                        var newCount = correctCount

                        if (next in guess[node]) {
                            newCount--
                        }

                        if (node in guess[next]) {
                            newCount++
                        }

                        result += dfsDp(next, newCount)
                    }
                }

                return result
            }

            return dfsDp(0, dfsGuess(0))
        }
    }

    expect {
        Solution().rootCount(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(4, 2),
            ), arrayOf(
                intArrayOf(1, 3),
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(2, 4),
            ), 1
        )
    }
}