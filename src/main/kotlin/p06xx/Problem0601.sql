package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findIntegers(n: Int): Int {
            val binNum = n.toString(2).drop(1).ifEmpty {
                return n + 1
            }

            val dp = Array(binNum.length) {
                intArrayOf(1, 1)
            }

            for (i in dp.lastIndex - 1 downTo 0) {
                dp[i][0] = dp[i + 1][0] + dp[i + 1][1]
                dp[i][1] = dp[i + 1][0]
            }

            var result = dp[0][0] + dp[0][1]

            var last = 1
            loop@ for (i in binNum.indices) {
                when {
                    binNum[i] == '0' -> {
                        last = 0
                    }

                    last == 1 -> {
                        result += dp[i][0]
                        return result
                    }

                    else -> {
                        result += dp[i][0]
                        last = 1
                    }
                }
            }

            result++

            return result
        }
    }

    measureTimeMillis {
        Solution().findIntegers(
            2
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}