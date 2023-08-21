package p15xx

import util.expect

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

    expect {
        Solution().mostVisited(
            3, intArrayOf(3, 1)
        )
    }
}

