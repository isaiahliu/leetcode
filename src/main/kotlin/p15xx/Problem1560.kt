package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mostVisited(n: Int, rounds: IntArray): List<Int> {
            val start = rounds[0]
            val end = rounds[rounds.lastIndex]

            return when {
                start > end -> {
                    (1..end) + (start..n)
                }

                else -> {
                    (start..end).toList()
                }
            }
        }
    }

    measureTimeMillis {
        Solution().mostVisited(
            3, intArrayOf(3, 1)
        ).also { println(it) }
    }
}

