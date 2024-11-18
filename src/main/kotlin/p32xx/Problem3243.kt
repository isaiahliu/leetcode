package p32xx

import util.expect

fun main() {
    class Solution {
        fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
            val distance = IntArray(n) { it }

            val nexts = Array(n) { sortedSetOf(compareByDescending { it }, it + 1) }
            nexts.last().clear()

            fun mark(pos: Int, d: Int) {
                if (distance[pos] > d) {
                    distance[pos] = d

                    nexts[pos].forEach {
                        mark(it, d + 1)
                    }
                }
            }
            return queries.map { (from, to) ->
                nexts[from] += to

                mark(to, distance[from] + 1)

                distance.last()
            }.toIntArray()
        }
    }

    expect {
        Solution().shortestDistanceAfterQueries(
            43,
            arrayOf(
               
            )
        )
    }
}
