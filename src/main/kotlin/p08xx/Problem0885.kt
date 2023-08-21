package p08xx

import util.expect

fun main() {
    class Solution {
        fun spiralMatrixIII(rows: Int, cols: Int, rStart: Int, cStart: Int): Array<IntArray> {
            var r = rStart
            var c = cStart

            val rRange = 0 until rows
            val cRange = 0 until cols

            var dr = 0
            var dc = 1

            var maxStep = 0

            val result = Array(rows * cols) {
                intArrayOf(r, c)
            }

            var resultIndex = 1

            var step = 0
            while (resultIndex < result.size) {
                r += dr
                c += dc

                if (r in rRange && c in cRange) {
                    result[resultIndex++].also {
                        it[0] = r
                        it[1] = c
                    }
                }

                step++
                if (step > maxStep / 2) {
                    maxStep++
                    step = 0

                    val t = dr
                    dr = dc
                    dc = -t
                }
            }

            return result
        }
    }
    expect {
        Solution().spiralMatrixIII(
            5, 6, 1, 4
        ).map { it.toList() }
    }
}