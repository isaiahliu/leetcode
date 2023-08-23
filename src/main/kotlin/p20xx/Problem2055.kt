package p20xx

import util.expect

fun main() {
    class Solution {
        fun platesBetweenCandles(s: String, queries: Array<IntArray>): IntArray {
            val counts = IntArray(s.length)
            val rightCounts = IntArray(s.length)
            val leftCounts = IntArray(s.length)

            var count = 0
            var rightCount = 0
            var leftCount = 0
            s.indices.forEach {
                if (s[it] == '*') {
                    count++
                    leftCount++
                    leftCounts[it] = leftCount
                } else {
                    leftCount = 0
                }
                counts[it] = count

                if (s[s.lastIndex - it] == '*') {
                    rightCount++
                    rightCounts[s.lastIndex - it] = rightCount
                } else {
                    rightCount = 0
                }
            }

            return queries.map { (from, to) ->
                (counts[to] - counts.getOrElse(from - 1) { 0 } - rightCounts[from] - leftCounts[to]).coerceAtLeast(0)
            }.toIntArray()
        }
    }

    expect {
        Solution().platesBetweenCandles(
            "***|**|*****|**||**|*", arrayOf(
                intArrayOf(4, 5)
            )
        )
    }
}