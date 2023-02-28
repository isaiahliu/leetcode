package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class NumMatrix(private val matrix: Array<IntArray>) {
        val sumCache = Array(matrix.size) { r ->
            IntArray(matrix[r].size) { c ->
                matrix[r][c]
            }
        }

        init {
            sumCache.forEachIndexed { r, row ->
                for (c in 1 until row.size) {
                    row[c] += row[c - 1]
                }

                if (r > 0) {
                    row.forEachIndexed { c, n ->
                        row[c] += sumCache[r - 1][c]
                    }
                }
            }
        }

        fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            var result = sumCache[row2][col2]

            if (row1 > 0) {
                result -= sumCache[row1 - 1][col2]
            }

            if (col1 > 0) {
                result -= sumCache[row2][col1 - 1]
            }

            if (row1 > 0 && col1 > 0) {
                result += sumCache[row1 - 1][col1 - 1]
            }
            return result
        }
    }

    measureTimeMillis {
        val m = NumMatrix(
            arrayOf(
                intArrayOf(3, 0, 1, 4, 2),
                intArrayOf(5, 6, 3, 2, 1),
                intArrayOf(1, 2, 0, 1, 5),
                intArrayOf(4, 1, 0, 1, 7),
                intArrayOf(1, 0, 3, 0, 5),
            )
        )

        m.sumRegion(1, 1, 2, 2).also { println(it) }
    }
}

