package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxEqualRowsAfterFlips(matrix: Array<IntArray>): Int {
            val map = hashMapOf<String, Int>()
            matrix.forEach { arr ->
                arr.joinToString("") {
                    if (arr[0] == 0) {
                        it
                    } else {
                        1 - it
                    }.toString()
                }.also {
                    map[it] = (map[it] ?: 0) + 1
                }
            }

            return map.values.max()
        }
    }

    measureTimeMillis {
        Solution().maxEqualRowsAfterFlips(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}