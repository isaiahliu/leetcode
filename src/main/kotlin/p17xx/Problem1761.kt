package p17xx

import util.expect

fun main() {
    class Solution {
        fun minTrioDegree(n: Int, edges: Array<IntArray>): Int {
            val adjacent = Array(n) { hashSetOf<Int>() }
            val degrees = IntArray(n)

            edges.forEach { (from, to) ->
                adjacent[(from - 1).coerceAtMost(to - 1)].add((from - 1).coerceAtLeast(to - 1))
                degrees[from - 1]++
                degrees[to - 1]++
            }

            var result = Int.MAX_VALUE
            for (i in 0 until n) {
                adjacent[i].forEach { j ->
                    adjacent[j].intersect(adjacent[i]).forEach {
                        result = result.coerceAtMost(degrees[i] + degrees[j] + degrees[it] - 6)
                    }
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minTrioDegree(
            6, arrayOf(
                intArrayOf(6, 5),
                intArrayOf(4, 3),
                intArrayOf(5, 1),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(4, 5),
                intArrayOf(2, 6),
                intArrayOf(1, 3)
            )
        )
    }
}
