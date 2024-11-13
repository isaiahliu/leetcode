package p32xx

import util.expect

fun main() {
    class Solution {
        fun countKConstraintSubstrings(s: String, k: Int, queries: Array<IntArray>): LongArray {
            val counts = intArrayOf(0, 0)

            val rightPos = IntArray(s.length) { s.lastIndex }
            val sums = LongArray(s.length)

            var from = 0
            var to = -1

            var result = 0L
            while (to < s.lastIndex) {
                to++
                counts[s[to] - '0']++

                while (counts.min() > k) {
                    counts[s[from] - '0']--
                    rightPos[from++] = to - 1
                }

                result += to - from + 1

                sums[to] = result
                rightPos[from] = to
            }

            return queries.map { (l, r) ->
                val m = minOf(r, rightPos[l])
                (m - l + 2L) * (m - l + 1) / 2 + sums[r] - sums.getOrElse(m) { 0L }
            }.toLongArray()
        }
    }

    expect {
        Solution().countKConstraintSubstrings(
            "010101", 1, arrayOf(
                intArrayOf(2, 3)
            )
        )
    }
}
