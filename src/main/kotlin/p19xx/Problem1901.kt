package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findPeakGrid(mat: Array<IntArray>): IntArray {
            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (arrayOf(
                            mat[r].getOrNull(c - 1) ?: -1,
                            mat[r].getOrNull(c + 1) ?: -1,
                            mat.getOrNull(r - 1)?.getOrNull(c) ?: -1,
                            mat.getOrNull(r + 1)?.getOrNull(c) ?: -1
                        ).all { it < num }
                    ) {
                        return intArrayOf(r, c)
                    }
                }
            }

            return intArrayOf(0, 0)
        }
    }

    measureTimeMillis {
        Solution().findPeakGrid(
            arrayOf()
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
