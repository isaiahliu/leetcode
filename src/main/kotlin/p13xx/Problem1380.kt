package p13xx

fun main() {
    class Solution {
        fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
            val rowMin = IntArray(matrix.size) { Int.MAX_VALUE }
            val columnMax = IntArray(matrix[0].size) { Int.MIN_VALUE }

            matrix.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    rowMin[r] = rowMin[r].coerceAtMost(num)
                    columnMax[c] = columnMax[c].coerceAtLeast(num)
                }
            }

            return rowMin.intersect(columnMax.toSet()).toList()
        }
    }

    println(
        Solution().luckyNumbers(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 7),
                intArrayOf(2, 4),
                intArrayOf(2, 6),
                intArrayOf(3, 5),
            )
        )
    )
}

