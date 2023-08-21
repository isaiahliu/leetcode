package p08xx

import util.expect

fun main() {
    class Solution {
        fun superEggDrop(k: Int, n: Int): Int {
            if (n == 1) {
                return 1
            }

            val dp = Array(n) {
                IntArray(k + 1)
            }

            repeat(k) {
                dp[0][it + 1] = 1
            }

            for (action in 1 until n) {
                for (egg in 1..k) {
                    dp[action][egg] = dp[action - 1][egg - 1] + dp[action - 1][egg] + 1
                }

                if (dp[action][k] >= n) {
                    return action + 1
                }
            }

            return 0
        }

        fun superEggDrop2(k: Int, n: Int): Int {
            val dp = Array(n + 1) {
                IntArray(k) { Int.MAX_VALUE }
            }

            repeat(k) {
                dp[0][it] = 0
            }

            repeat(n) {
                dp[it + 1][0] = it + 1
            }

            for (floor in 1..n) {
                for (egg in 1 until k) {
                    var left = 0
                    var right = floor - 1
                    while (right - left > 1) {
                        val mid = (left + right) / 2

                        val dp1 = dp[mid][egg]
                        val dp2 = dp[floor - mid - 1][egg - 1]

                        if (dp1 > dp2) {
                            right = mid - 1
                        } else if (dp1 < dp2) {
                            left = mid + 1
                        } else {
                            left = mid
                            right = mid
                        }
                    }

                    dp[floor][egg] = dp[left][egg].coerceAtLeast(dp[floor - left - 1][egg - 1])
                        .coerceAtMost(dp[right][egg].coerceAtLeast(dp[floor - right - 1][egg - 1])) + 1
                }
            }

            return dp[n][k - 1]
        }
    }

    expect {
        Solution().superEggDrop(3, 6)
        Solution().superEggDrop(2, 2)
        Solution().superEggDrop(2, 6)
        Solution().superEggDrop(2, 9)
        Solution().superEggDrop(3, 14)
    }
}