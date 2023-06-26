package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumSum(arr: IntArray): Int {
            val dp = Array(arr.size) { intArrayOf(arr[it], Int.MIN_VALUE) }

            var result = arr[0]

            dp[0][0] = arr[0]

            for (i in 1 until arr.size) {
                dp[i][0] = dp[i - 1][0].coerceAtLeast(0) + arr[i]
                dp.getOrNull(i - 2)?.get(0)?.also {
                    dp[i - 1][1].coerceAtLeast(it).takeIf { it > 0 }?.also {
                        dp[i][1] = arr[i] + it
                    }
                }

                result = result.coerceAtLeast(dp[i][0]).coerceAtLeast(dp[i][1])
            }

            return result
        }
    }

    measureTimeMillis {
        println(Solution().maximumSum(intArrayOf(1, -2, 0, 3)))
    }.also { println("Time cost: ${it}ms") }
}

