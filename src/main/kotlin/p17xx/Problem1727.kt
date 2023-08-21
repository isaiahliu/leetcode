package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun largestSubmatrix(matrix: Array<IntArray>): Int {
            val sizes = Array(matrix.size) {
                TreeMap<Int, Int>(compareByDescending { it })
            }

            for (r in matrix.indices) {
                val row = matrix[r]
                val lastRow = matrix.getOrNull(r - 1)
                val rowSizes = sizes[r]

                for (c in matrix[0].indices) {
                    if (row[c] > 0) {
                        row[c] += lastRow?.get(c) ?: 0

                        rowSizes[row[c]] = (rowSizes[row[c]] ?: 0) + 1
                    }
                }
            }

            var result = 0

            sizes.forEach { rowSizes ->
                var size = 0
                var maxHeight = Int.MAX_VALUE
                rowSizes.forEach { (height, s) ->
                    size += s
                    maxHeight = maxHeight.coerceAtMost(height)

                    result = result.coerceAtLeast(size * maxHeight)
                }
            }

            return result
        }
    }

    expect {
        Solution().largestSubmatrix(
            arrayOf()
        )
    }
}
