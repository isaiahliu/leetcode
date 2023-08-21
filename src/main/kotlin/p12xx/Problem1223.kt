package p12xx

import java.math.BigInteger

fun main() {
    class Solution {
        var base = BigInteger.ZERO

        val m = (1e9 + 7).toInt()

        fun dieSimulator(n: Int, rollMax: IntArray): Int {
            val dp = Array(n) {
                if (it == 0) {
                    LongArray(rollMax.size) { 1 }
                } else {
                    LongArray(rollMax.size)
                }
            }

            for (i in 1 until n) {
                rollMax.forEachIndexed { index, max ->
                    if (i < max) {
                        repeat(rollMax.size) { ti ->
                            dp[i][index] += dp[i - 1][ti]
                        }
                    } else {
                        for (offset in i - 1 downTo (i - max).coerceAtLeast(0)) {
                            repeat(rollMax.size) { ti ->
                                if (index != ti) {
                                    dp[i][index] += dp[offset][ti]
                                }
                            }
                        }
                    }
                }

                repeat(rollMax.size) {
                    dp[i][it] = dp[i][it] % m
                }
            }
            return (dp.last().sum() % m.toLong()).toInt()
        }
    }

    Solution().dieSimulator(20, intArrayOf(8, 5, 10, 8, 7, 2))
}
