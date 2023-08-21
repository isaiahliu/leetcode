package p11xx

import util.expect

fun main() {
    class Solution {
        fun canMakePaliQueries(s: String, queries: Array<IntArray>): List<Boolean> {
            var count = 0
            val counts = IntArray(s.length) {
                count = count xor (1 shl (s[it] - 'a'))

                count
            }
            return queries.map { (from, to, replace) ->
                val fromCount = counts.getOrNull(from - 1) ?: 0
                val toCount = counts[to]

                val oddCount = (fromCount xor toCount).countOneBits()

                replace * 2 >= oddCount - (to - from + 1) % 2
            }
        }
    }

    expect {
        Solution().canMakePaliQueries(
            "zu", arrayOf(
                intArrayOf(0, 1, 2)
            )
        )
    }
}