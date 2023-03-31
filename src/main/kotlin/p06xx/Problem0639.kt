package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numDecodings(s: String): Int {
            if (s.isEmpty()) {
                return 1
            }

            val dp = LongArray(s.length)

            val m = 1000000007

            fun process(index: Int, num: Int): Boolean {
                when (num) {
                    0 -> {
                        when (s.getOrNull(index - 1)) {
                            '*' -> {
                                dp[index] += (dp.getOrNull(index - 2) ?: 1) * 2
                            }

                            '1', '2' -> {
                                dp[index] += (dp.getOrNull(index - 2) ?: 1)

                            }

                            else -> {
                                return false
                            }
                        }
                    }

                    in 1..6 -> {
                        dp[index] += (dp.getOrNull(index - 1) ?: 1)
                        when (s.getOrNull(index - 1)) {
                            in '1'..'2' -> {
                                dp[index] += (dp.getOrNull(index - 2) ?: 1)
                            }

                            '*' -> {
                                dp[index] += (dp.getOrNull(index - 2) ?: 1) * 2
                            }
                        }
                    }

                    else -> {
                        dp[index] += (dp.getOrNull(index - 1) ?: 1)

                        when (s.getOrNull(index - 1)) {
                            '1', '*' -> {
                                dp[index] += (dp.getOrNull(index - 2) ?: 1)
                            }
                        }
                    }
                }

                dp[index] = dp[index] % m

                return true
            }

            s.forEachIndexed { index, c ->
                if (c == '*') {
                    repeat(9) {
                        process(index, it + 1)
                    }
                } else {
                    if (!process(index, c - '0')) {
                        return 0
                    }
                }
            }

            return dp[dp.lastIndex].toInt()
        }
    }

    measureTimeMillis {
        Solution().numDecodings(
            "2*"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}