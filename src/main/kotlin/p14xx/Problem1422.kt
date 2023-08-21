package p14xx

import util.expect

fun main() {
    class Solution {
        fun maxScore(s: String): Int {
            val leftZeroSum = IntArray(s.length - 1) { s.getOrNull(it)?.let { '1' - it } ?: 0 }
            val rightOneSum = IntArray(s.length - 1) { s.getOrNull(it + 1)?.let { it - '0' } ?: 0 }

            for (i in 1 until leftZeroSum.size) {
                leftZeroSum[i] += leftZeroSum[i - 1]
            }

            for (i in rightOneSum.lastIndex - 1 downTo 0) {
                rightOneSum[i] += rightOneSum[i + 1]
            }

            var result = 0

            repeat(s.length - 1) {
                result = result.coerceAtLeast(leftZeroSum[it] + rightOneSum[it])
            }

            return result
        }
    }

    expect {
        Solution().maxScore(
            "1111"
        )
    }
}

