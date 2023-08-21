package p13xx

import util.expect

fun main() {
    class Solution {
        fun minTaps(n: Int, ranges: IntArray): Int {
            val sortedRanges = ranges.mapIndexed { index, i ->
                index - i..index + i
            }.filter { it.first < it.last }.sortedBy { it.first }

            var result = 0
            var rangeIndex = 0
            var endIndex = 0
            while (endIndex < n) {
                var newEndIndex = endIndex
                while (rangeIndex < sortedRanges.size && sortedRanges[rangeIndex].first <= endIndex && newEndIndex < n) {
                    newEndIndex = newEndIndex.coerceAtLeast(sortedRanges[rangeIndex++].last)
                }

                result++
                if (newEndIndex <= endIndex) {
                    return -1
                }
                endIndex = newEndIndex
            }

            return result
        }
    }

    expect {
        Solution().minTaps(
            5, intArrayOf(3, 4, 1, 1, 0, 0)
        )
    }
}

