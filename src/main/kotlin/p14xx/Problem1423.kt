package p14xx

import util.expect

fun main() {
    class Solution {
        fun maxScore(cardPoints: IntArray, k: Int): Int {
            var sum = 0
            repeat(k) {
                sum += cardPoints[it]
            }

            var result = sum

            repeat(k) {
                sum -= cardPoints[k - it - 1]
                sum += cardPoints[cardPoints.lastIndex - it]

                result = result.coerceAtLeast(sum)
            }

            return result
        }
    }

    expect {
        Solution().maxScore(
            intArrayOf(), 5
        )
    }
}

