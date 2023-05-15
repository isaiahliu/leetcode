package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxEqualRowsAfterFlips(matrix: Array<IntArray>): Int {
            val map = hashMapOf<String, Int>()
            var result = 0
            matrix.forEach { arr ->
                arr.joinToString("") {
                    (it xor arr[0]).toString()
                }.also {
                    map[it] = ((map[it] ?: 0) + 1).also {
                        result = result.coerceAtLeast(it)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxEqualRowsAfterFlips(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}