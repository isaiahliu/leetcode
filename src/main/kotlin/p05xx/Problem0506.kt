package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRelativeRanks(score: IntArray): Array<String> {
            val map = score.mapIndexed { index, s -> s to index }.toMap()

            val result = Array(score.size) { "" }

            score.sortedDescending().forEachIndexed { seq, s ->
                map[s]?.also {
                    result[it] = when (seq) {
                        0 -> "Gold Medal"
                        1 -> "Silver Medal"
                        2 -> "Bronze Medal"
                        else -> (seq + 1).toString()
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findRelativeRanks(
            intArrayOf()
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}