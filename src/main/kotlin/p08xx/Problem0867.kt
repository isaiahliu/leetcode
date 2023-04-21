package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun transpose(matrix: Array<IntArray>): Array<IntArray> {
            return Array(matrix[0].size) { c ->
                IntArray(matrix.size) { r ->
                    matrix[r][c]
                }
            }
        }
    }

    measureTimeMillis {
        Solution().transpose(
            arrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}