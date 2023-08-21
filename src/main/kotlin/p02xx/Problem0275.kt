package p02xx

import util.expect

fun main() {
    class Solution {
        fun hIndex(citations: IntArray): Int {
            val size = citations.size
            var resultIndex = size

            fun binarySearch(startIndex: Int, endIndex: Int) {
                if (startIndex > endIndex) {
                    return
                }

                val midIndex = startIndex + (endIndex - startIndex) / 2
                val midNum = citations[midIndex]

                if (midNum >= size - midIndex) {
                    resultIndex = midIndex
                    binarySearch(startIndex, midIndex - 1)
                } else {
                    binarySearch(midIndex + 1, endIndex)
                }
            }

            binarySearch(0, size - 1)

            return size - resultIndex
        }
    }

    expect {
        Solution().hIndex(
            intArrayOf(100)
        )
    }
}

