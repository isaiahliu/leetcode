package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRightInterval(intervals: Array<IntArray>): IntArray {
            val rangeMap = intervals.withIndex().associate { (i, r) ->
                r[0] to r[1] to i
            }

            val sortedRange = rangeMap.keys.sortedBy { it.first }

            val result = IntArray(intervals.size) { -1 }

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val (from, to) = sortedRange[midIndex]

                return if (from == target) {
                    midIndex
                } else if (from < target) {
                    binarySearch(midIndex + 1, endIndex, target)
                } else {
                    binarySearch(startIndex, midIndex - 1, target) ?: midIndex
                }
            }

            sortedRange.forEachIndexed { i, pair ->
                binarySearch(i, sortedRange.lastIndex, pair.second)?.also {
                    val ind1 = rangeMap[pair]!!
                    val ind2 = rangeMap[sortedRange[it]]!!
                    result[ind1] = ind2
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().findRightInterval(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(3, 4),
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


