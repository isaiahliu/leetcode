package p10xx

import util.expect

fun main() {
    class Solution {
        fun maxScoreSightseeingPair(values: IntArray): Int {
            var bestScore = -9999
            var bestIndex = -1

            var result = Int.MIN_VALUE
            values.forEachIndexed { index, score ->
                result = result.coerceAtLeast(bestScore + score + bestIndex - index)

                if (score - bestScore > bestIndex - index) {
                    bestScore = score
                    bestIndex = index
                }
            }

            return result
        }
    }

    expect {
        Solution().maxScoreSightseeingPair(
            intArrayOf(1, 2, 2)
        )
    }
}
