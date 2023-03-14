package p16xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().restoreMatrix(
            intArrayOf(5, 7, 10), intArrayOf(8, 6, 8)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}