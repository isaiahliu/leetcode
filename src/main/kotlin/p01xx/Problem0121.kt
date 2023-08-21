package p01xx

import util.expect

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

    expect {
        Solution().maxProfit(intArrayOf())
    }
}

