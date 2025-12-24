package p36xx

import util.expect

fun main() {
    class Solution {
        fun maxProfit(prices: IntArray, strategy: IntArray, k: Int): Long {
            var sum = 0L

            val prevKeeps = LongArray(prices.size)
            val postSales = LongArray(prices.size)

            prices.indices.forEach {
                sum += strategy[it] * prices[it]

                prevKeeps[it] = (prevKeeps.getOrNull(it - 1) ?: 0) + prices[it] * strategy[it] * -1L
                postSales[prices.lastIndex - it] = (postSales.getOrNull(prices.lastIndex - it + 1) ?: 0) + prices[prices.lastIndex - it] * (1L - strategy[prices.lastIndex - it])
            }

            return sum + maxOf((k - 1 until prices.size).maxOf {
                postSales[it - k / 2 + 1] - (postSales.getOrNull(it + 1) ?: 0) + prevKeeps[it - k / 2] - (prevKeeps.getOrNull(it - k) ?: 0)
            }, 0)
        }
    }

    expect {
        Solution().maxProfit(
            intArrayOf(4, 7, 13), intArrayOf(-1, -1, 0), 2
        )
    }
}
