package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
            val m = mat.size
            val n = mat[0].size

            if (m * n != r * c) {
                return mat
            }

            var m1 = 0
            var n1 = 0

            return Array(r) {
                IntArray(c) {
                    mat[m1][n1].also {
                        n1++

                        if (n1 == n) {
                            m1++
                            n1 = 0
                        }
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().matrixReshape(
            arrayOf(), 1, 2
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}