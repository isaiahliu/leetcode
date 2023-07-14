package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSideLength(mat: Array<IntArray>, threshold: Int): Int {
            val sums = Array(mat.size) { r ->
                Array(mat[r].size) { c ->
                    intArrayOf(mat[r][c], mat[r][c])
                }
            }

            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    sums[r].getOrNull(c - 1)?.also {
                        sums[r][c][0] += it[0]
                    }

                    sums.getOrNull(r - 1)?.getOrNull(c)?.also {
                        sums[r][c][1] += it[1]
                    }
                }
            }

            var result = 0
            sums.forEachIndexed { r, row ->
                row.forEachIndexed loop@{ c, _ ->
                    var sum = 0

                    var size = result + 1
                    repeat(size) { rowOffset ->
                        sums.getOrNull(r + rowOffset)?.getOrNull(c + size - 1)?.also {
                            sum += it[0]
                            sums[r + rowOffset].getOrNull(c - 1)?.also {
                                sum -= it[0]
                            }
                        } ?: return@loop
                    }

                    if (sum <= threshold) {
                        result = size

                        while (true) {
                            val addSum = sums.getOrNull(r + size)?.getOrNull(c + size) ?: break

                            sum += addSum[0] + addSum[1] - mat[r + size][c + size]

                            sums[r + size].getOrNull(c - 1)?.also {
                                sum -= it[0]
                            }

                            sums.getOrNull(r - 1)?.getOrNull(c + size)?.also {
                                sum -= it[1]
                            }

                            if (sum > threshold) {
                                break
                            }
                            size++
                            result = size
                        }
                    }
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().maxSideLength(
            arrayOf(
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
                intArrayOf(1, 1, 3, 2, 4, 3, 2),
            ), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
