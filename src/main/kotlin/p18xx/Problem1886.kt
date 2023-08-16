package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRotation(mat: Array<IntArray>, target: Array<IntArray>): Boolean {
            val n = mat.lastIndex

            loop@ for (numGetter in arrayOf<(Int, Int) -> Int>(
                { r, c -> mat[r][c] },
                { r, c -> mat[n - c][r] },
                { r, c -> mat[n - r][n - c] },
                { r, c -> mat[c][n - r] }
            )) {
                for ((rowIndex, row) in target.withIndex()) {
                    for ((columnIndex, num) in row.withIndex()) {
                        if (num != numGetter(rowIndex, columnIndex)) {
                            continue@loop
                        }
                    }
                }

                return true
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().findRotation(
            arrayOf(), arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
