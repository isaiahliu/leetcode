package p16xx

import util.expect

fun main() {
    class Solution {
        fun stoneGameVII(stones: IntArray): Int {
            var sum = 0
            val sums = IntArray(stones.size) {
                sum += stones[it]
                sum
            }

            val dp = Array(stones.size) {
                IntArray(stones.size)
            }

            for (delta in 1 until stones.size) {
                for (startIndex in 0 until stones.size - delta) {
                    val endIndex = startIndex + delta

                    dp[startIndex][endIndex] =
                        (sums[endIndex] - sums[startIndex] - dp[startIndex + 1][endIndex]).coerceAtLeast(
                            sums[endIndex - 1] - sums.getOrElse(startIndex - 1) { 0 } - dp[startIndex][endIndex - 1])
                }
            }

            return dp[0].last()
        }
    }

    expect {
        Solution().stoneGameVII(
            intArrayOf()
        )
    }
}

