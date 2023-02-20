package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray): Int {
            var min = prices[0]
            var result = 0
            prices.forEach {
                result = result.coerceAtLeast(it - min)

                min = min.coerceAtMost(it)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxProfit(intArrayOf()).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

