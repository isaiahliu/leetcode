package p06xx

import util.expect

fun main() {
    class Solution {
        fun kInversePairs(n: Int, k: Int): Int {
            return when {
                k == 0 -> {
                    1
                }

                k == 1 -> {
                    n - 1
                }

                n <= 1 -> {
                    0
                }

                else -> {
                    val m = 1000000007
                    var dp = LongArray(k + 1)

                    dp[0] = 1

                    repeat(n - 1) { num ->
                        for (i in 1 until dp.size) {
                            dp[i] = (dp[i] + dp[i - 1]) % m
                        }

                        dp = LongArray(k + 1) {
                            (dp[it] - (dp.getOrNull(it - num - 2) ?: 0) + m) % m
                        }
                    }

                    return dp[dp.lastIndex].toInt()
                }
            }
        }
    }

    expect {
        Solution().kInversePairs(1000, 1000)

    }
}