package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canMakePaliQueries(s: String, queries: Array<IntArray>): List<Boolean> {
            var count = 0L
            val counts = LongArray(s.length) {
                count = count xor (1L shl (s[it] - 'a'))

                count
            }
            return queries.map { (from, to, replace) ->
                val fromCount = counts.getOrNull(from - 1) ?: 0L
                val toCount = counts[to]

                val oddCount = (fromCount xor toCount).countOneBits()

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