package p10xx

import util.expect

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

    expect {
        Solution().maxEqualRowsAfterFlips(
            arrayOf()
        )
    }
}