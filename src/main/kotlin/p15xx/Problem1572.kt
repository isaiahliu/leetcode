package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun diagonalSum(mat: Array<IntArray>): Int {
            var result = 0

            mat.forEachIndexed { index, row ->
                val op = mat.lastIndex - index

                result += row[index]

                if (op != index) {
                    result += row[op]
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().diagonalSum(
            arrayOf()
        ).also { println(it) }
    }
}

