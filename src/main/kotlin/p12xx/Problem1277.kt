package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSquares(matrix: Array<IntArray>): Int {
            val sums = Array(matrix.size) {
                Array(matrix[it].size) {
                    intArrayOf(0, 0)
                }
            }

            matrix.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0) {
                        sums[r][c][0] = 1 + (sums[r].getOrNull(c - 1)?.getOrNull(0) ?: 0)
                        sums[r][c][1] = 1 + (sums.getOrNull(r - 1)?.getOrNull(c)?.getOrNull(1) ?: 0)
                    }
                }
            }

            var result = 0

            sums.forEachIndexed { r, row ->
                row.forEachIndexed { c, s ->
                    if (s[0] > 0) {
                        result++

                        var size = 1

                        while (true) {
                            val more = sums.getOrNull(r + size)?.getOrNull(c + size) ?: break

                            if (more[0] <= size || more[1] <= size) {
                                break
                            }

                            result++
                            size++
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countSquares(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
