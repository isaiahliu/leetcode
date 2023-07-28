package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSubmat(mat: Array<IntArray>): Int {
            mat.forEach {
                for (i in 1 until it.size) {
                    if (it[i] > 0) {
                        it[i] += it[i - 1]
                    }
                }
            }

            var result = 0
            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    var t = r
                    var size = Int.MAX_VALUE
                    while (t >= 0 && mat[t][c] > 0) {
                        size = size.coerceAtMost(mat[t][c])

                        result += size
                        t--
                    }
                }
            }
            return result
        }
    }
    measureTimeMillis {
        Solution().numSubmat(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1),
            )
        ).also { println(it) }
    }
}

