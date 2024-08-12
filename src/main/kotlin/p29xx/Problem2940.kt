package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
            val result = IntArray(queries.size) { -1 }

            val heightIndices = TreeMap<Int, MutableSet<Int>>(compareByDescending { it })
            heights.forEachIndexed { index, i ->
                heightIndices.computeIfAbsent(i) { mutableSetOf() }.add(index)
            }

            val sortedQueries = TreeMap<Int, MutableSet<Pair<Int, Int>>>(compareByDescending { it })
            queries.forEachIndexed { index, (q1, q2) ->
                val (l, r) = arrayOf(q1, q2).sorted()

                if (heights[r] > heights[l] || l == r) {
                    result[index] = r
                } else {
                    sortedQueries.computeIfAbsent(heights[l]) { mutableSetOf() }.add(r to index)
                }
            }

            val buildings = TreeMap<Int, Int>()

            while (sortedQueries.isNotEmpty() && heightIndices.isNotEmpty()) {
                val (height, indices) = heightIndices.pollFirstEntry()

                while (sortedQueries.isNotEmpty()) {
                    sortedQueries.firstKey()?.takeIf { it >= height }?.also {
                        sortedQueries.pollFirstEntry().value.forEach { (rIndex, queryIndex) ->
                            buildings.higherKey(rIndex)?.also {
                                result[queryIndex] = it
                            }
                        }
                    } ?: break
                }

                indices.forEach {
                    buildings[it] = height
                }
            }

            while (sortedQueries.isNotEmpty()) {
                sortedQueries.firstKey()?.also {
                    sortedQueries.pollFirstEntry().value.forEach { (rIndex, queryIndex) ->
                        buildings.higherEntry(rIndex)?.value?.also {
                            result[queryIndex] = it
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().leftmostBuildingQueries(
            intArrayOf(6, 4, 8, 5, 2, 7), arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 3),
                intArrayOf(2, 4),
                intArrayOf(3, 4),
                intArrayOf(2, 2),
            )
        )
    }
}
