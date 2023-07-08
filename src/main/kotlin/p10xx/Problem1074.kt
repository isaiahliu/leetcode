package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSubmatrixSumTarget(matrix: Array<IntArray>, target: Int): Int {
            var result = 0
            val m = matrix.size
            val n = matrix[0].size
            for (i in 0 until m) {
                val sum = IntArray(n)
                for (j in i until m) {
                    for (c in 0 until n) {
                        sum[c] += matrix[j][c]
                    }
                    val map: MutableMap<Int, Int> = hashMapOf()
                    map[0] = 1
                    var count = 0
                    var pre = 0
                    for (s in sum) {
                        pre += s

                        map[pre - target]?.also {
                            count += it
                        }
                        map[pre] = (map[pre] ?: 0) + 1
                    }
                    result += count
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().numSubmatrixSumTarget(
            arrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
