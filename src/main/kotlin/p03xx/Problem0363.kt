package p03xx

import util.expect

fun main() {
    class Solution {
        fun maxSumSubmatrix(matrix: Array<IntArray>, k: Int): Int {
            var max = Int.MIN_VALUE

            matrix.forEachIndexed { rowIndex, row ->
                var sum = 0

                row.forEachIndexed { columnIndex, num ->
                    sum += num

                    row[columnIndex] = sum
                }

                if (rowIndex > 0) {
                    val lastRow = matrix[rowIndex - 1]

                    row.forEachIndexed { columnIndex, _ ->
                        row[columnIndex] += lastRow[columnIndex]
                    }
                }
            }

            for (baseR in matrix.indices) {
                for (r in 0..baseR) {
                    for (baseC in matrix[r].indices) {
                        for (c in 0..baseC) {
                            var sum = matrix[baseR][baseC]
                            matrix.getOrNull(r - 1)?.getOrNull(baseC)?.also {
                                sum -= it
                            }

                            matrix.getOrNull(baseR)?.getOrNull(c - 1)?.also {
                                sum -= it
                            }

                            matrix.getOrNull(r - 1)?.getOrNull(c - 1)?.also {
                                sum += it
                            }

                            if (sum < k) {
                                max = max.coerceAtLeast(sum)
                            } else if (sum == k) {
                                return k
                            }
                        }
                    }
                }
            }

            return max
        }
    }

    expect {
        Solution().maxSumSubmatrix(
            arrayOf(
                intArrayOf(5, -4, -3, 4),
                intArrayOf(-3, -4, 4, 5),
                intArrayOf(5, 1, 5, -4),
            ), 3
        )
    }
}

