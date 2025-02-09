package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumCoins(prices: IntArray): Int {
            val dp = TreeMap<Int, Int>()

            dp[2] = prices[0]

            for (index in 1 until prices.size) {
                val sum = dp.ceilingEntry(index).value + prices[index]

                val freeToIndex = index * 2 + 2

                while (dp.lastEntry().value >= sum) {
                    dp.pollLastEntry()
                }

                dp[freeToIndex] = sum
            }

            return dp.ceilingEntry(prices.size).value
        }
    }

    expect {
        Solution().minimumCoins(
            intArrayOf(1, 10, 1, 1)
        )
    }
}
