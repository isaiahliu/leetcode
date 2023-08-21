package p01xx

import util.expect

fun main() {
    class Solution {
        fun maxProfit(k: Int, prices: IntArray): Int {
            if (prices.isEmpty()) {
                return 0
            }

            val washedPrices = arrayListOf<Pair<Int, Int>>()

            var min = prices[0]
            var max = min

            prices.forEach {
                if (it > max) {
                    max = it
                } else {
                    if (min < max) {
                        washedPrices.add(min to max)
                    }

                    min = it
                    max = it
                }
            }

            if (min < max) {
                washedPrices.add(min to max)
            }

            if (k >= washedPrices.size) {
                return washedPrices.map { (min, max) -> max - min }.sum()
            }

            val dp = Array(washedPrices.size) { i ->
                Array(k + 1) {
                    //purchase -- sale
                    intArrayOf(-washedPrices[i].first, 0)
                }
            }

            dp[0][1].also {
                it[1] = washedPrices[0].second - washedPrices[0].first
            }

            for (i in 1 until dp.size) {
                for (tx in 1..k) {
                    //purchase
                    //keep last purchase
                    val p1 = dp[i - 1][tx][0]
                    //do purchase this turn
                    val p2 = dp[i - 1][tx - 1][1] - washedPrices[i].first

                    dp[i][tx][0] = p1.coerceAtLeast(p2)

                    //sale
                    //keep last sale
                    val s1 = dp[i - 1][tx][1]
                    //do sale this turn
                    val s2 = dp[i][tx][0] + washedPrices[i].second

                    dp[i][tx][1] = s1.coerceAtLeast(s2)
                }
            }

            return dp[washedPrices.lastIndex][k][1]
        }
    }

    expect {
        Solution().maxProfit(
            3,
            intArrayOf(2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8)
        )
    }
}

