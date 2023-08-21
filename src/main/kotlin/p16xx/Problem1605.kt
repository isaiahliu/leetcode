package p16xx

import util.expect

fun main() {
    class Solution {
        fun restoreMatrix(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
            return Array(rowSum.size) { r ->
                IntArray(colSum.size) { c ->
                    rowSum[r].coerceAtMost(colSum[c]).also {
                        rowSum[r] -= it
                        colSum[c] -= it
                    }
                }
            }
        }
    }

    expect {
        Solution().restoreMatrix(
            intArrayOf(5, 7, 10), intArrayOf(8, 6, 8)
        )
    }
}