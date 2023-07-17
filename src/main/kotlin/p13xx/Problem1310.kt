package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun xorQueries(arr: IntArray, queries: Array<IntArray>): IntArray {
            for (i in 1 until arr.size) {
                arr[i] = arr[i] xor arr[i - 1]
            }

            return queries.map { (left, right) ->
                arr[right] xor (arr.getOrNull(left - 1) ?: 0)
            }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().xorQueries(
            intArrayOf(), arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

