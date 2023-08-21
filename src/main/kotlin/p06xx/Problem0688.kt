package p06xx

import util.expect

fun main() {
    class Solution {
        fun knightProbability(n: Int, k: Int, row: Int, column: Int): Double {
            var map = hashMapOf<Pair<Int, Int>, Double>()
            map[row to column] = 1.0

            val range = 0 until n
            repeat(k) {
                val newMap = hashMapOf<Pair<Int, Int>, Double>()

                map.forEach { (p, rate) ->
                    val (r, c) = p

                    arrayOf(
                        r - 2 to c - 1,
                        r - 1 to c - 2,
                        r + 1 to c - 2,
                        r + 2 to c - 1,
                        r - 2 to c + 1,
                        r - 1 to c + 2,
                        r + 1 to c + 2,
                        r + 2 to c + 1
                    ).filter { it.first in range && it.second in range }.forEach {
                        newMap[it] = (newMap[it] ?: 0.0) + rate / 8
                    }
                }

                map = newMap
            }

            return map.values.sum()
        }
    }

    expect {
        Solution().knightProbability(
            8, 30, 6, 4
        )
    }
}