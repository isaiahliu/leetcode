package p09xx

import util.expect

fun main() {
    class Solution {
        fun distinctSubseqII(s: String): Int {
            val m = 1000000007L
            var total = 1L

            val counts = LongArray(26)

            s.forEach {
                val thisSum = total - counts[it - 'a']

                counts[it - 'a'] += thisSum
                counts[it - 'a'] %= m

                total += thisSum
                total %= m
            }

            return ((total - 1 + m) % m).toInt()
        }
    }

    expect {
        Solution().distinctSubseqII(
            "abc"
        )
    }
}