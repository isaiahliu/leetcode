package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray): Int {
            var result = 0
            var min = prices[0]
            (prices + 0).forEachIndexed { index, num ->
                if (index > 0) {
                    if (num < prices[index - 1]) {
                        result += prices[index - 1] - min
                        min = num
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxProfit(intArrayOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

