package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
            var deltaR = -1
            var deltaC = 1

            var r = 1
            var c = -1
            return IntArray(mat.size * mat[0].size) {
                r += deltaR
                c += deltaC

                mat.getOrNull(r)?.getOrNull(c) ?: run {
                    if (deltaR < 0) {
                        r++
                        if (mat.getOrNull(r)?.getOrNull(c) == null) {
                            r++
                            c--
                        }
                    } else {
                        c++
                        if (mat.getOrNull(r)?.getOrNull(c) == null) {
                            r--
                            c++
                        }
                    }

                    deltaR *= -1
                    deltaC *= -1
                    mat[r][c]
                }
            }
        }
    }

    measureTimeMillis {
        Solution().findDiagonalOrder(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}