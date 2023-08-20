package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxMatrixSum(matrix: Array<IntArray>): Long {
            var min = Int.MAX_VALUE
            var result = 0L
            var sign = 1
            matrix.forEach {
                it.forEach {
                    when {
                        it > 0 -> {
                            result += it
                            min = min.coerceAtMost(it)
                        }

                        it < 0 -> {
                            result -= it
                            min = min.coerceAtMost(-it)
                            sign = -sign
                        }

                        else -> {
                            sign = 0
                        }
                    }
                }
            }

            if (sign < 0) {
                result -= min * 2
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxMatrixSum(
            arrayOf()
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}