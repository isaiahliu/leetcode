package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canMakePaliQueries(s: String, queries: Array<IntArray>): List<Boolean> {
            var result = 0L
            val sums = LongArray(s.length) {
                result = result xor (1L shl (s[it] - 'a'))

                result
            }
            return queries.map { (from, to, replace) ->
                val toSum = sums[to]
                val fromSum = sums.getOrNull(from - 1) ?: 0L

                val oddCount = java.lang.Long.bitCount(fromSum xor toSum)

                replace * 2 >= oddCount - (to - from + 1) % 2
            }
        }
    }

    measureTimeMillis {
        Solution().canMakePaliQueries(
            "zu", arrayOf(
                intArrayOf(0, 1, 2)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}