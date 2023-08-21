package p09xx

import util.expect

fun main() {
    class Solution {
        fun minFlipsMonoIncr(s: String): Int {
            val oneCounts = IntArray(s.length + 1)
            val zeroCounts = IntArray(s.length + 1)

            val sum = IntArray(2)
            for (i in s.indices) {
                sum[0] += '1' - s[s.lastIndex - i]
                sum[1] += s[i] - '0'

                zeroCounts[s.lastIndex - i] = sum[0]
                oneCounts[i + 1] = sum[1]
            }

            if (sum[0] == 0 || sum[1] == 0) {
                return 0
            }

            var result = Int.MAX_VALUE

            for (i in oneCounts.indices) {
                result = result.coerceAtMost(zeroCounts[i] + oneCounts[i])
            }

            return result
        }
    }

    expect {
        Solution().minFlipsMonoIncr(
            "00011000"
        )
    }
}