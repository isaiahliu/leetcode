package p23xx

import util.expect

fun main() {
    class Solution {
        fun edgeScore(edges: IntArray): Int {
            val scores = LongArray(edges.size)

            edges.forEachIndexed { from, i ->
                scores[i] += from.toLong()
            }

            return scores.indices.maxWith(compareBy<Int> { scores[it] }.thenByDescending { it })
        }
    }

    expect {
        Solution().edgeScore(
            intArrayOf()
        )
    }
}