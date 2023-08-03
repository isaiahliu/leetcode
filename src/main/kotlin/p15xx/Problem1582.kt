package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSpecial(mat: Array<IntArray>): Int {
            val rowSums = IntArray(mat.size)
            val columnSums = IntArray(mat[0].size)

            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    rowSums[r] += num
                    columnSums[c] += num
                }
            }

            var result = 0
            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num == 1 && rowSums[r] == 1 && columnSums[c] == 1) {
                        result++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numSpecial(
            arrayOf(
                intArrayOf(3, 1, 2),
                intArrayOf(3, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf(1, 2, 4),
                intArrayOf(1, 1, 2),
                intArrayOf(2, 3, 4),
            )
        ).also { println(it) }
    }
}

