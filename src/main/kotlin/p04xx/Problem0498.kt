package p04xx

import util.expect

fun main() {
    class Solution {
        fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
            var deltaR = -1
            var deltaC = 1

            var r = 0
            var c = 0

            fun inRange(): Boolean = r in mat.indices && c in mat[0].indices

            return IntArray(mat.size * mat[0].size) {
                if (!inRange()) {
                    if (deltaR < 0) {
                        r++
                        if (!inRange()) {
                            r++
                            c--
                        }
                    } else {
                        c++
                        if (!inRange()) {
                            r--
                            c++
                        }
                    }

                    deltaR *= -1
                    deltaC *= -1
                }

                mat[r][c].also {
                    r += deltaR
                    c += deltaC
                }
            }
        }
    }

    expect {
        Solution().findDiagonalOrder(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
        ).toList()
    }
}