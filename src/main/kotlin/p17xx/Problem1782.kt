package p17xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(n: Int, edges: Array<IntArray>, queries: IntArray): IntArray {
            val degrees = IntArray(n)
            val adjacent = hashMapOf<Pair<Int, Int>, Int>()
            edges.forEach { (from, to) ->
                degrees[from - 1]++
                degrees[to - 1]++

                ((from - 1).coerceAtMost(to - 1) to (from - 1).coerceAtLeast(to - 1)).also {
                    adjacent[it] = (adjacent[it] ?: 0) + 1
                }
            }

            val degreeCount = degrees.toList().groupingBy { it }.eachCount()

            val counts = IntArray(degreeCount.keys.max() * 2 + 1)

            degreeCount.toList().also {
                for (i in it.indices) {
                    val (size1, count1) = it[i]

                    counts[size1 * 2] += count1 * (count1 - 1) / 2

                    for (j in i + 1 until it.size) {
                        val (size2, count2) = it[j]

                        counts[size1 + size2] += count1 * count2
                    }
                }
            }

            adjacent.forEach { (pair, count) ->
                (degrees[pair.first] + degrees[pair.second]).also {
                    counts[it]--
                    counts[it - count]++
                }
            }

            for (i in counts.lastIndex downTo 1) {
                counts[i - 1] += counts[i]
            }

            return queries.map {
                counts.getOrNull(it + 1) ?: 0
            }.toIntArray()
        }
    }

    expect {
        Solution().countPairs(
            5,
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(1, 3),
                intArrayOf(1, 4),
            ),
            intArrayOf(0, 1, 2)
        ).toList()
    }
}
