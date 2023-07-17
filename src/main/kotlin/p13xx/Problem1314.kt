package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
            val m = mat.size
            val n = mat[0].size
            repeat(m) { r ->
                repeat(n) { c ->
                    mat[r].getOrNull(c - 1)?.also {
                        mat[r][c] += it
                    }
                }

                repeat(n) { c ->
                    mat.getOrNull(r - 1)?.getOrNull(c)?.also {
                        mat[r][c] += it
                    }
                }
            }

            return Array(m) { r ->
                IntArray(n) { c ->
                    var result = mat[(r + k).coerceAtMost(m - 1)][(c + k).coerceAtMost(n - 1)]

                    mat.getOrNull(r - k - 1)?.getOrNull((c + k).coerceAtMost(n - 1))?.also {
                        result -= it
                    }

                    mat[(r + k).coerceAtMost(m - 1)].getOrNull(c - k - 1)?.also {
                        result -= it
                    }

                    mat.getOrNull(r - k - 1)?.getOrNull(c - k - 1)?.also {
                        result += it
                    }

                    result
                }
            }
        }
    }

    measureTimeMillis {
        Solution().matrixBlockSum(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9),
            ), 1
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

